package com.snake.operation.platform.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snake.operation.platform.mapper.MenuEntityMapper;
import com.snake.operation.platform.model.dto.MenuDTO;
import com.snake.operation.platform.model.entity.MenuEntity;
import com.snake.operation.platform.model.form.MenuForm;
import com.snake.operation.platform.model.form.SyncTenantMenuForm;
import com.snake.operation.platform.model.queries.MenuPageEqualsQueries;
import com.snake.operation.platform.service.MenuEntityService;
import io.github.yxsnake.pisces.web.core.base.BaseFuzzyQueries;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;
import io.github.yxsnake.pisces.web.core.enums.DeletedEnum;
import io.github.yxsnake.pisces.web.core.enums.DisabledEnum;
import lombok.extern.slf4j.Slf4j;
import io.github.yxsnake.pisces.web.core.utils.BizAssert;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
@Slf4j
@Service
public class MenuEntityServiceImpl extends ServiceImpl<MenuEntityMapper, MenuEntity> implements MenuEntityService {
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean create(MenuForm form) {
        String menuId = IdWorker.getIdStr();
        MenuEntity menuEntity = form.convert(MenuEntity.class);
        menuEntity.setPlatformResourceId(menuId);
        menuEntity.setDeleted(DeletedEnum.NORMAL.getValue());
        menuEntity.setDisabled(DisabledEnum.NORMAL.getValue());
        String parentId = form.getParentId();

        if(StrUtil.isBlank(parentId)){
            menuEntity.setParentId(MenuEntity.ROOT);
            menuEntity.setLevel(MenuEntity.ROOT_LEVEL);
        }else{
            MenuEntity parentMenu = this.lambdaQuery().eq(MenuEntity::getPlatformResourceId, parentId)
                    .list().stream().findFirst().orElse(null);

            BizAssert.isTrue("上级菜单不存在", Objects.isNull(parentMenu));
        }
        menuEntity.setSort(DateUtil.date().getTime());
        // 校验菜单权限是否重复
        if(StrUtil.isNotBlank(form.getPerm())){
            int size = this.lambdaQuery().eq(MenuEntity::getDeleted, DeletedEnum.NORMAL.getValue())
                    .eq(MenuEntity::getPerm, form.getPerm()).list().size();
            BizAssert.isTrue("权限标识已存在,请检查",size>0);
        }
        // 菜单名称是否重复
        int size = this.lambdaQuery().eq(MenuEntity::getDeleted, DeletedEnum.NORMAL.getValue())
                .eq(MenuEntity::getName, form.getName()).list().size();
        BizAssert.isTrue("菜单名称已存在,请检查",size>0);

        this.save(menuEntity);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean modify(MenuForm form) {
        MenuEntity menuEntity = this.lambdaQuery()
                .eq(MenuEntity::getPlatformResourceId,form.getPlatformResourceId())
                .eq(MenuEntity::getDeleted,DeletedEnum.NORMAL.getValue())
                .list()
                .stream().findFirst().orElse(null);
        BizAssert.isTrue("菜单不存在",Objects.isNull(menuEntity));
        String parentId = menuEntity.getParentId();
        BeanUtils.copyProperties(form,menuEntity);
        // 防止上级菜单被覆盖修改
        menuEntity.setParentId(parentId);
        this.getBaseMapper().updateById(menuEntity);
        return Boolean.TRUE;
    }


    @Override
    public void removeByPlatformMenuId(String platformMenuId) {
        MenuEntity menuEntity = this.lambdaQuery().eq(MenuEntity::getDeleted, DeletedEnum.NORMAL.getValue())
                .eq(MenuEntity::getPlatformResourceId, platformMenuId).list().stream().findFirst().orElse(null);
        menuEntity.setDeleted(DeletedEnum.DEL.getValue());
        this.getBaseMapper().updateById(menuEntity);
    }

    @Override
    public IPage<MenuDTO> pageList(QueryFilter<MenuPageEqualsQueries, BaseFuzzyQueries> queryFilter) {
        BizAssert.isTrue("请求参数不能为空",Objects.isNull(queryFilter));
        MenuPageEqualsQueries equalsQueries = queryFilter.getEqualsQueries();
        BizAssert.isTrue("equalsQueries参数不能为空",Objects.isNull(equalsQueries));
        IPage<MenuDTO> page = this.page(new Page<>(queryFilter.getPageNum(), queryFilter.getPageSize()),
                Wrappers.lambdaQuery(MenuEntity.class)
                        .eq(MenuEntity::getDeleted, DeletedEnum.NORMAL.getValue())
                        .like(StrUtil.isNotBlank(equalsQueries.getMenuName()), MenuEntity::getName, equalsQueries.getMenuName())
        ).convert(item -> item.convert(MenuDTO.class));
        return page;
    }

    @Override
    public Boolean syncTenant(SyncTenantMenuForm form) {
        return null;
    }

}
