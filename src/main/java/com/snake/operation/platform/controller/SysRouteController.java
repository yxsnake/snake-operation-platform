package com.snake.operation.platform.controller;

import com.snake.operation.platform.model.dto.SysRouteDTO;
import com.snake.operation.platform.service.SysRouteService;
import io.github.yxsnake.pisces.web.core.base.Result;
import io.github.yxsnake.pisces.web.core.framework.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */

@Tag(name = "运营平台菜单路由")
@Slf4j
@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE,produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class SysRouteController extends BaseController {

    private final SysRouteService sysRouteService;

    @Operation(summary = "获取当前用户菜单路由")
    @GetMapping(value = "/get-async-routes")
    public ResponseEntity<Result<List<SysRouteDTO>>> getAsyncRoutes(){
        return success(sysRouteService.getAsyncRoutes());
    }
}
