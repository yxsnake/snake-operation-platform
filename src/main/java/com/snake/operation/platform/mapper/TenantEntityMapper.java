package com.snake.operation.platform.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.snake.operation.platform.model.entity.TenantEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description: 租户相关 Mapper
 * @version: 1.0
 */
@Mapper
public interface TenantEntityMapper extends BaseMapper<TenantEntity> {
}
