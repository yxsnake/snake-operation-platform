package com.snake.operation.platform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.snake.operation.platform.model.dto.TenantDTO;
import com.snake.operation.platform.model.form.TenantAuditForm;
import com.snake.operation.platform.model.form.TenantForm;
import com.snake.operation.platform.model.queries.TenantPageEqualQueries;
import com.snake.operation.platform.service.TenantEntityService;
import io.github.yxsnake.pisces.web.core.base.BaseFuzzyQueries;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.github.yxsnake.pisces.web.core.base.Result;
import io.github.yxsnake.pisces.web.core.framework.controller.BaseController;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description: 租户相关
 * @version: 1.0
 */
@Tag(name = "租户相关API")
@Slf4j
@RestController
@RequestMapping(value = "/tenant",consumes = MediaType.APPLICATION_JSON_VALUE,produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class TenantController extends BaseController {

    private final TenantEntityService tenantEntityService;


    @Operation(summary = "创建租户")
    @PostMapping(value = "/create")
    public ResponseEntity<Result<Boolean>> create(@Validated(TenantForm.Create.class) @RequestBody TenantForm form){
        return success(tenantEntityService.create(form));
    }

    @Operation(summary = "分页查询租户列表")
    @PostMapping(value = "/page-list")
    public ResponseEntity<Result<IPage<TenantDTO>>> pageList(@RequestBody QueryFilter<TenantPageEqualQueries, BaseFuzzyQueries> queryFilter){
        return success(tenantEntityService.pageList(queryFilter));
    }

    @Operation(summary = "修改租户")
    @PostMapping(value = "/modify")
    public ResponseEntity<Result<Boolean>> modify(@Validated(TenantForm.Modify.class) @RequestBody TenantForm form){
        return success(tenantEntityService.modify(form));
    }

    @Operation(summary = "审核并同步初始化租户")
    @PostMapping(value = "/audit")
    public ResponseEntity<Result<Boolean>> audit(@RequestBody TenantAuditForm form){
        return success(tenantEntityService.audit(form));
    }




}
