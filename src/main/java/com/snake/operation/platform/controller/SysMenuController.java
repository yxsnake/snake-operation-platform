package com.snake.operation.platform.controller;

import com.snake.operation.platform.service.SysMenuService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: snake
 * @create-time: 2024-07-29
 * @description:
 * @version: 1.0
 */
@Tag(name = "运营平台系统菜单")
@Slf4j
@RestController
@RequestMapping(value = "/sys-menu")
@RequiredArgsConstructor
public class SysMenuController {

    private final SysMenuService sysMenuService;



}
