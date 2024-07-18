package com.snake.operation.platform.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.snake.operation.platform.common.SupperAdminCommon;
import com.snake.operation.platform.mapper.TenantEntityMapper;
import com.snake.operation.platform.model.entity.ModuleEntity;
import com.snake.operation.platform.model.entity.TenantEntity;
import com.snake.operation.platform.model.entity.TenantResourceEntity;
import com.snake.operation.platform.model.form.TenantAuditForm;
import com.snake.operation.platform.model.form.TenantForm;
import com.snake.operation.platform.service.ModuleEntityService;
import com.snake.operation.platform.service.TenantEntityService;
import com.snake.operation.platform.service.TenantResourceEntityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        List<String> moduleIds = form.getModuleIds();
        // 查询 模块信息
        List<ModuleEntity> moduleEntities = moduleEntityService.lambdaQuery().in(ModuleEntity::getModuleId, moduleIds).list();
        List<TenantResourceEntity> tenantResourceEntities = Lists.newArrayList();
        for (ModuleEntity moduleEntity : moduleEntities) {
            TenantResourceEntity tenantResourceEntity = new TenantResourceEntity();
            tenantResourceEntity.setId(IdWorker.getIdStr());
            tenantResourceEntity.setTenantId(tenantId);

        }


        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean modify(TenantForm form) {
        return null;
    }

    @Override
    public Boolean audit(TenantAuditForm form) {
        return null;
    }
}
