package com.snake.operation.platform.controller;

import com.snake.operation.platform.model.form.MenuForm;
import com.snake.operation.platform.service.MenuEntityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.github.yxsnake.pisces.web.core.base.Result;
import io.github.yxsnake.pisces.web.core.framework.controller.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description: 菜单
 * @version: 1.0
 */
@Tag(name = "菜单相关API")
@Slf4j
@RestController
@RequestMapping(value = "/menu")
@RequiredArgsConstructor
public class MenuController extends BaseController {

    private final MenuEntityService menuEntityService;

    @PostMapping(value = "/create")
    public ResponseEntity<Result<Boolean>> create(@Validated(MenuForm.Create.class) @RequestBody MenuForm form){
        return success(menuEntityService.create(form));
    }

    @PostMapping(value = "/modify")
    public ResponseEntity<Result<Boolean>> modify(@Validated(MenuForm.Modify.class) @RequestBody MenuForm form){
        return success(menuEntityService.modify(form));
    }

    @GetMapping(value = "/sync-tenant")
    public ResponseEntity<Result<Boolean>> syncTenant(@RequestParam("tenantId") String tenantId){
        return success(menuEntityService.syncTenant(tenantId));
    }


}
