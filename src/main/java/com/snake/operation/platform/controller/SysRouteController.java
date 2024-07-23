package com.snake.operation.platform.controller;

import com.snake.operation.platform.model.dto.SysRouteDTO;
import com.snake.operation.platform.service.SysRouteService;
import io.github.yxsnake.pisces.web.core.base.Result;
import io.github.yxsnake.pisces.web.core.framework.controller.BaseController;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Tag(name = "运营平台登录")
@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class SysRouteController extends BaseController {

    private final SysRouteService sysRouteService;

    @GetMapping(value = "/get-async-routes")
    public ResponseEntity<Result<List<SysRouteDTO>>> getAsyncRoutes(){
        return success(sysRouteService.getAsyncRoutes());
    }
}
