package com.snake.operation.platform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.snake.operation.platform.model.dto.MenuDTO;
import com.snake.operation.platform.model.form.MenuForm;
import com.snake.operation.platform.model.form.SyncTenantMenuForm;
import com.snake.operation.platform.model.queries.MenuPageEqualsQueries;
import com.snake.operation.platform.service.MenuEntityService;
import io.github.yxsnake.pisces.web.core.base.BaseFuzzyQueries;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.github.yxsnake.pisces.web.core.base.Result;
import io.github.yxsnake.pisces.web.core.framework.controller.BaseController;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Operation(summary = "创建菜单")
    @PostMapping(value = "/create")
    public ResponseEntity<Result<Boolean>> create(@Validated(MenuForm.Create.class) @RequestBody MenuForm form){
        return success(menuEntityService.create(form));
    }

    @Operation(summary = "修改菜单")
    @PostMapping(value = "/modify")
    public ResponseEntity<Result<Boolean>> modify(@Validated(MenuForm.Modify.class) @RequestBody MenuForm form){
        return success(menuEntityService.modify(form));
    }

    @Operation(summary = "菜单分页查询")
    @PostMapping(value = "/pageList")
    public ResponseEntity<Result<IPage<MenuDTO>>> pageList(@RequestBody QueryFilter<MenuPageEqualsQueries, BaseFuzzyQueries> queryFilter){
        return success(menuEntityService.pageList(queryFilter));
    }


    @Operation(summary = "删除平台菜单")
    @GetMapping(value = "/remove")
    public ResponseEntity<Result<Boolean>> remove(@RequestParam("platformMenuId") String platformMenuId){
        menuEntityService.removeByPlatformMenuId(platformMenuId);
        return success(Boolean.TRUE);
    }

    @Operation(summary = "同步租户菜单")
    @GetMapping(value = "/sync-tenant")
    public ResponseEntity<Result<Boolean>> syncTenant(@RequestBody SyncTenantMenuForm form){
        return success(menuEntityService.syncTenant(form));
    }


}
