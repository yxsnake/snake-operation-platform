package com.snake.operation.platform.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snake.operation.platform.mapper.ApiResourceEntityMapper;
import com.snake.operation.platform.model.entity.ApiResourceEntity;
import com.snake.operation.platform.model.entity.MenuEntity;
import com.snake.operation.platform.model.form.ApiResourceForm;
import com.snake.operation.platform.service.ApiResourceEntityService;
import com.snake.operation.platform.service.MenuEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.github.yxsnake.pisces.web.core.enums.DeletedEnum;
import io.github.yxsnake.pisces.web.core.enums.DisabledEnum;
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
@RequiredArgsConstructor
public class ApiResourceEntityServiceImpl extends ServiceImpl<ApiResourceEntityMapper,ApiResourceEntity> implements ApiResourceEntityService {

    private final MenuEntityService menuEntityService;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(ApiResourceForm form) {
        // 校验上级资源是否存在
        this.checkParentResourceExist(form.getParentId());
        // 表单数据转换
        ApiResourceEntity apiResourceEntity = form.convert(ApiResourceEntity.class);
        // 校验 接口路径是否唯一
        this.checkApiPath(form.getPlatformResourceId(), form.getPath());
        // 校验 接口权限标识是否唯一
        this.checkApiPerm(form.getPlatformResourceId(), form.getPerm());

        apiResourceEntity.setPlatformResourceId(IdWorker.getIdStr());
        apiResourceEntity.setDeleted(DeletedEnum.NORMAL.getValue());
        apiResourceEntity.setDisabled(DisabledEnum.NORMAL.getValue());
        apiResourceEntity.setSort(DateUtil.date().getTime());
        apiResourceEntity.setCreateTime(DateUtil.date());
        this.save(apiResourceEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void modify(ApiResourceForm form) {
        // 校验上级资源是否存在
        this.checkParentResourceExist(form.getParentId());

        ApiResourceEntity apiResourceEntity = this.getBaseMapper().selectById(form.getPlatformResourceId());

        // 校验 接口路径是否唯一
        this.checkApiPath(form.getPlatformResourceId(), form.getPath());
        // 校验 接口权限标识是否唯一
        this.checkApiPerm(form.getPlatformResourceId(), form.getPerm());

        BeanUtils.copyProperties(form,apiResourceEntity);
        apiResourceEntity.setUpdateTime(DateUtil.date());
        this.getBaseMapper().updateById(apiResourceEntity);
    }

    @Override
    public Boolean syncTenant(String tenantId) {
        BizAssert.isTrue("请选择租户下发同步",StrUtil.isBlank(tenantId));
        Boolean flag = Boolean.FALSE;
        if(StrUtil.equals("all",tenantId)){
            // 全网下发
        }else {
            // 单个租户下发

        }
        return flag;
    }

    private void checkApiPath(String platformResourceId,String path){
        int size = this.lambdaQuery()
                .eq(ApiResourceEntity::getPath, path)
                .ne(StrUtil.isNotBlank(platformResourceId),ApiResourceEntity::getPlatformResourceId,platformResourceId)
                .list().size();
        BizAssert.isTrue("接口路径已存在",size>0);
    }

    private void checkApiPerm(String platformResourceId,String perm){
        int size = this.lambdaQuery()
                .eq(ApiResourceEntity::getPerm, perm)
                .ne(StrUtil.isNotBlank(platformResourceId),ApiResourceEntity::getPlatformResourceId,platformResourceId)
                .list().size();
        BizAssert.isTrue("接口权限标识已存在",size>0);
    }

    private void checkParentResourceExist(String parentId){
        MenuEntity menuEntity = menuEntityService.getBaseMapper().selectById(parentId);
        BizAssert.isTrue("上级资源不存在", Objects.isNull(menuEntity));
    }
}
