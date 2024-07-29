package com.snake.operation.platform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.snake.operation.platform.model.dto.SysUserDTO;
import com.snake.operation.platform.model.form.SysUserEnableOrDisableForm;
import com.snake.operation.platform.model.form.SysUserForm;
import com.snake.operation.platform.model.fuzzy.SysUserFuzzyQueries;
import com.snake.operation.platform.model.queries.SysUserPageEqualsQueries;
import com.snake.operation.platform.service.SysUserService;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;
import io.github.yxsnake.pisces.web.core.base.Result;
import io.github.yxsnake.pisces.web.core.framework.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: snake
 * @create-time: 2024-07-29
 * @description:
 * @version: 1.0
 */
@Tag(name = "运营平台用户")
@Slf4j
@RestController
@RequestMapping(value = "/sys-user")
@RequiredArgsConstructor
public class SysUserController extends BaseController {

    private final SysUserService sysUserService;

    @Operation(summary = "分页查询运营平台用户列表")
    @PostMapping(value = "/page-list")
    public ResponseEntity<Result<IPage<SysUserDTO>>> pageList(@RequestBody QueryFilter<SysUserPageEqualsQueries, SysUserFuzzyQueries> queryFilter){
        return success(sysUserService.pageList(queryFilter));
    }

    @Operation(summary = "创建运营平台用户")
    @PostMapping(value = "/create")
    public ResponseEntity<Result<SysUserDTO>> create(@Validated(SysUserForm.Create.class) @RequestBody SysUserForm sysUserForm){
        return success(sysUserService.create(sysUserForm));
    }

    @Operation(summary = "修改运营平台用户")
    @PostMapping(value = "/modify")
    public ResponseEntity<Result<SysUserDTO>> modify(@Validated(SysUserForm.Modify.class) @RequestBody SysUserForm sysUserForm){
        return success(sysUserService.modify(sysUserForm));
    }

    @Operation(summary = "查询运营平台用户详情")
    @GetMapping(value = "/detail")
    public ResponseEntity<Result<SysUserDTO>> detail(@RequestParam("userId") String userId){
        return success(sysUserService.detail(userId));
    }

    @Operation(summary = "启用或禁用运营平台用户")
    @PostMapping(value = "/enable-or-disable")
    public ResponseEntity<Result<SysUserDTO>> enableOrDisable(@RequestBody SysUserEnableOrDisableForm form){
        return success(sysUserService.enableOrDisable(form));
    }
}
