package com.snake.operation.platform.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snake.operation.platform.mapper.MenuEntityMapper;
import com.snake.operation.platform.model.entity.MenuEntity;
import com.snake.operation.platform.model.form.MenuForm;
import com.snake.operation.platform.service.MenuEntityService;
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
        this.save(menuEntity);
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean modify(MenuForm form) {
        MenuEntity menuEntity = this.getBaseMapper().selectById(form.getPlatformResourceId());
        BizAssert.isTrue("菜单不存在",Objects.isNull(menuEntity));
        String parentId = menuEntity.getParentId();
        BeanUtils.copyProperties(form,menuEntity);
        // 防止上级菜单被覆盖修改
        menuEntity.setParentId(parentId);
        this.getBaseMapper().updateById(menuEntity);
        return Boolean.TRUE;
    }

    @Override
    public Boolean syncTenant(String tenantId) {
        return null;
    }
}
