package com.snake.operation.platform.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.snake.operation.platform.common.SupperAdminCommon;
import com.snake.operation.platform.mapper.TenantEntityMapper;
import com.snake.operation.platform.model.dto.TenantDTO;
import com.snake.operation.platform.model.entity.*;
import com.snake.operation.platform.model.enums.TenantAuditStatusEnum;
import com.snake.operation.platform.model.form.TenantAuditForm;
import com.snake.operation.platform.model.form.TenantForm;
import com.snake.operation.platform.model.queries.TenantPageEqualQueries;
import com.snake.operation.platform.retrofit.client.TenantClient;
import com.snake.operation.platform.retrofit.model.form.*;
import com.snake.operation.platform.service.*;
import io.github.yxsnake.pisces.web.core.base.BaseFuzzyQueries;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;
import io.github.yxsnake.pisces.web.core.base.Result;
import io.github.yxsnake.pisces.web.core.enums.DeletedEnum;
import io.github.yxsnake.pisces.web.core.enums.DisabledEnum;
import io.github.yxsnake.pisces.web.core.utils.BizAssert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TenantEntityServiceImpl extends ServiceImpl<TenantEntityMapper, TenantEntity> implements TenantEntityService {

    private final TenantResourceEntityService tenantResourceEntityService;

    private final ModuleEntityService moduleEntityService;

    private final MenuEntityService menuEntityService;

    private final ApiResourceEntityService apiResourceEntityService;

    private final TenantClient tenantClient;



    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean create(TenantForm form) {
        TenantEntity tenantEntity = form.convert(TenantEntity.class);
        String tenantId = IdWorker.getIdStr();
        tenantEntity.setTenantId(tenantId);
        // 获取一个随机账号 并查询是否已存在
        String supperAdminAccount = SupperAdminCommon.getSupperAdminAccount();
        tenantEntity.setSupperAccount(supperAdminAccount);
        tenantEntity.setSupperPassword(form.getPhone());
        tenantEntity.setCreateTime(DateUtil.date());
        tenantEntity.setAuditStatus(TenantAuditStatusEnum.TODO.getValue());

        List<String> moduleIds = form.getModuleIds();
        // 查询 模块信息
        List<ModuleEntity> moduleEntities = moduleEntityService.lambdaQuery()
                .eq(ModuleEntity::getDeleted, DeletedEnum.NORMAL.getValue())
                .in(ModuleEntity::getModuleId, moduleIds)
                .list();
        BizAssert.isTrue("存在已删除的模块",moduleEntities.size()-moduleIds.size()!=0);
        // 查询 菜单列表
        List<MenuEntity> list = menuEntityService.lambdaQuery().eq(MenuEntity::getDeleted, DeletedEnum.NORMAL.getValue())
                .eq(MenuEntity::getDisabled, DisabledEnum.NORMAL.getValue())
                .in(MenuEntity::getModuleId, moduleIds)
                .list();
        BizAssert.isTrue("未查到模块下的菜单信息", CollUtil.isEmpty(list));
        Map<String, List<MenuEntity>> listMap = list.stream().collect(Collectors.groupingBy(MenuEntity::getModuleId));
        List<TenantResourceEntity> tenantResourceEntities = Lists.newArrayList();
        for (ModuleEntity moduleEntity : moduleEntities) {
            String moduleId = moduleEntity.getModuleId();
            List<MenuEntity> menuEntities = listMap.get(moduleId);
            for (MenuEntity menuEntity : menuEntities) {
                TenantResourceEntity tenantResourceEntity = new TenantResourceEntity();
                tenantResourceEntity.setId(IdWorker.getIdStr());
                tenantResourceEntity.setTenantId(tenantId);
                tenantResourceEntity.setMenuId(menuEntity.getPlatformResourceId());
                tenantResourceEntity.setModuleId(moduleId);
                tenantResourceEntity.setProductId(moduleEntity.getProductId());
                tenantResourceEntities.add(tenantResourceEntity);
            }
        }
        tenantResourceEntityService.saveBatch(tenantResourceEntities);
        this.save(tenantEntity);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean modify(TenantForm form) {
        TenantEntity tenantEntity = this.lambdaQuery().eq(TenantEntity::getDeleted, DeletedEnum.NORMAL.getValue())
                .eq(TenantEntity::getTenantId, form.getTenantId()).list().stream().findFirst().orElse(null);
        BizAssert.isTrue("租户信息不存在",Objects.isNull(tenantEntity));
        if(StrUtil.isNotBlank(form.getTenantName())){
            tenantEntity.setTenantName(form.getTenantName());
        }
        if(StrUtil.isNotBlank(form.getEmail())){
            tenantEntity.setEmail(form.getEmail());
        }
        if(StrUtil.isNotBlank(form.getPhone())){
            tenantEntity.setPhone(form.getPhone());
        }
        if(StrUtil.isNotBlank(form.getIntroduction())){
            tenantEntity.setIntroduction(form.getIntroduction());
        }
        tenantEntity.setUpdateTime(DateUtil.date());
        this.updateById(tenantEntity);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean audit(TenantAuditForm form) {
        // 查询
        TenantEntity tenantEntity = this.lambdaQuery().eq(TenantEntity::getTenantId, form.getTenantId())
                .list().stream().findFirst().orElse(null);
        BizAssert.isTrue("租户信息不存在",Objects.isNull(tenantEntity));
        BizAssert.isTrue("租户已审核初始化无需再次审核",tenantEntity.getAuditStatus().equals(TenantAuditStatusEnum.PASS.getValue()));
        TenantAuditStatusEnum tenantAuditStatusEnum = TenantAuditStatusEnum.getInstance(form.getAuditStatus());
        BizAssert.isTrue("审核状态不能为空",Objects.isNull(tenantAuditStatusEnum));

        tenantEntity.setAuditStatus(tenantAuditStatusEnum.getValue());
        tenantEntity.setAuditRemark(form.getAuditRemark());
        this.updateById(tenantEntity);
        if(TenantAuditStatusEnum.PASS.equals(tenantAuditStatusEnum)){
            // 查询租户购买的模块信息
            List<TenantResourceEntity> tenantResourceEntities = tenantResourceEntityService.lambdaQuery()
                    .in(TenantResourceEntity::getTenantId, form.getTenantId()).list();
            Set<String> moduleIds = tenantResourceEntities.stream().map(TenantResourceEntity::getModuleId).collect(Collectors.toSet());

            PlatformInitTenantForm initTenantForm = new PlatformInitTenantForm();
            // 租户信息
            InitTenantInfoForm initTenantInfoForm = tenantEntity.convert(InitTenantInfoForm.class);
            initTenantForm.setTenantInfoForm(initTenantInfoForm);

            // 查询 角色信息
            List<InitTenantRoleInfoForm> roleInfoForms = initTenantBuildRoles();
            initTenantForm.setRoleFormList(roleInfoForms);

            // 查询 资源菜单信息
            List<InitTenantMenuForm> menuForms = initTenantBuildMenus(moduleIds);
            initTenantForm.setMenuFormList(menuForms);

            // 查询 api 资源信息
            List<InitTenantApiResourceForm> apiResourceForms = initTenantBuildApiResources();
            initTenantForm.setApiResourceFormList(apiResourceForms);
            Result<Boolean> result = tenantClient.initTenant(initTenantForm);
            if(!Result.isSuccess(result)){
                BizAssert.isTrue("租户初始化失败",true);
            }
            return result.getData();

        }
        return Boolean.TRUE;
    }

    @Override
    public IPage<TenantDTO> pageList(QueryFilter<TenantPageEqualQueries, BaseFuzzyQueries> queryFilter) {
        BizAssert.isTrue("查询参数不能为空", Objects.isNull(queryFilter));
        TenantPageEqualQueries equalsQueries = queryFilter.getEqualsQueries();
        BizAssert.isTrue("equalsQueries参数不能为空",Objects.isNull(equalsQueries));
        IPage<TenantDTO> page = this.page(new Page<>(queryFilter.getPageNum(), queryFilter.getPageSize()),
                Wrappers.lambdaQuery(TenantEntity.class)
                        .eq(TenantEntity::getDeleted, DeletedEnum.NORMAL.getValue())
                        .like(StrUtil.isNotBlank(equalsQueries.getTenantName()), TenantEntity::getTenantName, equalsQueries.getTenantName())
        ).convert(item -> item.convert(TenantDTO.class));
        return page;
    }

    private List<InitTenantRoleInfoForm> initTenantBuildRoles(){
        List<InitTenantRoleInfoForm> roleInfoForms = Lists.newArrayList();
        return roleInfoForms;
    }


    private List<InitTenantApiResourceForm> initTenantBuildApiResources(){
        List<InitTenantApiResourceForm> apiResourceForms = Lists.newArrayList();
        List<ApiResourceEntity> list = apiResourceEntityService.lambdaQuery()
                .eq(ApiResourceEntity::getDeleted,DeletedEnum.NORMAL.getValue())
                .eq(ApiResourceEntity::getDisabled,DisabledEnum.NORMAL.getValue())
                .list();
        for (ApiResourceEntity apiResourceEntity : list) {
            InitTenantApiResourceForm form = new InitTenantApiResourceForm();
            BeanUtils.copyProperties(apiResourceEntity,form);
            apiResourceForms.add(form);
        }
        return apiResourceForms;
    }
    private List<InitTenantMenuForm> initTenantBuildMenus(Set<String> moduleIds){
        List<InitTenantMenuForm> menuInfoForms = Lists.newArrayList();
        List<MenuEntity> list = menuEntityService.lambdaQuery().eq(MenuEntity::getDisabled, DisabledEnum.NORMAL.getValue())
                .eq(MenuEntity::getDeleted, DeletedEnum.NORMAL.getValue())
                .in(MenuEntity::getModuleId, moduleIds).list();
        for (MenuEntity menuEntity : list) {
            InitTenantMenuForm form = new InitTenantMenuForm();
            BeanUtils.copyProperties(menuEntity,form);
            form.setPlatformMenuId(menuEntity.getPlatformResourceId());
            form.setPlatformParentId(menuEntity.getParentId());
            menuInfoForms.add(form);
        }
        return menuInfoForms;
    }
}
