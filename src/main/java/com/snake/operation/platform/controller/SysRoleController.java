package com.snake.operation.platform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.snake.operation.platform.model.dto.SysRoleDTO;
import com.snake.operation.platform.model.form.RoleAuthForm;
import com.snake.operation.platform.model.form.SysRoleForm;
import com.snake.operation.platform.model.form.SysUserForm;
import com.snake.operation.platform.model.fuzzy.SysRoleFuzzyQueries;
import com.snake.operation.platform.model.queries.SysRolePageEqualsQueries;
import com.snake.operation.platform.service.SysRoleService;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;
import io.github.yxsnake.pisces.web.core.base.Result;
import io.github.yxsnake.pisces.web.core.framework.controller.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: snake
 * @create-time: 2024-07-29
 * @description:
 * @version: 1.0
 */
@Tag(name = "运营平台系统角色")
@Slf4j
@RestController
@RequestMapping(value = "/sys-role",produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class SysRoleController extends BaseController {

    private final SysRoleService sysRoleService;

    @Operation(summary = "分页查询运营平台角色列表")
    @PostMapping(value = "/page-list")
    public ResponseEntity<Result<IPage<SysRoleDTO>>> pageList(@RequestBody QueryFilter<SysRolePageEqualsQueries, SysRoleFuzzyQueries> queryFilter){
        return success(sysRoleService.pageList(queryFilter));
    }

    @Operation(summary = "创建运营平台角色")
    @PostMapping(value = "/create")
    public ResponseEntity<Result<SysRoleDTO>> create(@Validated(SysUserForm.Create.class) @RequestBody SysRoleForm form){
        return success(sysRoleService.create(form));
    }

    @Operation(summary = "修改运营平台角色")
    @PostMapping(value = "/modify")
    public ResponseEntity<Result<SysRoleDTO>> modify(@Validated(SysUserForm.Modify.class) @RequestBody SysRoleForm form){
        return success(sysRoleService.modify(form));
    }

    @Operation(summary = "查询运营平台角色详情")
    @GetMapping(value = "/detail")
    public ResponseEntity<Result<SysRoleDTO>> detail(@RequestParam("roleId") String roleId){
        return success(sysRoleService.detail(roleId));
    }

    @Operation(summary = "删除角色")
    @GetMapping(value = "/remove")
    public ResponseEntity<Result<SysRoleDTO>> removeByRoleId(@RequestParam("roleId") String roleId){
        return success(sysRoleService.removeByRoleId(roleId));
    }

    @Operation(summary = "角色授权菜单")
    @PostMapping(value = "/roleAuth")
    public ResponseEntity<Result<Boolean>> roleAuth(@Validated @RequestBody RoleAuthForm form){
        sysRoleService.roleAuth(form);
        return success(Boolean.TRUE);
    }
}
