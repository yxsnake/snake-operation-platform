package com.snake.operation.platform.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.snake.operation.platform.model.dto.SysRouteDTO;
import com.snake.operation.platform.service.SysMenuService;
import com.snake.operation.platform.service.SysRouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SysRouteServiceImpl implements SysRouteService {

    private final SysMenuService sysMenuService;
    @Override
    public List<SysRouteDTO> getAsyncRoutes() {
        // 获取当前用户 ID
        String userId = String.valueOf(StpUtil.getLoginId());
        // 查询用户菜单树
//        sysMenuService.getUserTree(userId);
        return null;
    }
}
