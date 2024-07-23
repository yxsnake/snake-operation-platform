package com.snake.operation.platform.service;

import com.snake.operation.platform.model.dto.SysRouteDTO;

import java.util.List;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */
public interface SysRouteService {
    List<SysRouteDTO> getAsyncRoutes();
}
