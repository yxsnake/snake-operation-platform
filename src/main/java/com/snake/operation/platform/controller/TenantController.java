package com.snake.operation.platform.controller;

import com.snake.operation.platform.model.form.TenantAuditForm;
import com.snake.operation.platform.model.form.TenantForm;
import com.snake.operation.platform.service.TenantEntityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.github.yxsnake.pisces.web.core.base.Result;
import io.github.yxsnake.pisces.web.core.framework.controller.BaseController;
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
@RequestMapping(value = "/tenant")
@RequiredArgsConstructor
public class TenantController extends BaseController {

    private final TenantEntityService tenantEntityService;


    @PostMapping(value = "/create")
    public ResponseEntity<Result<Boolean>> create(@Validated(TenantForm.Create.class) @RequestBody TenantForm form){
        return success(tenantEntityService.create(form));
    }

    @PostMapping(value = "/modify")
    public ResponseEntity<Result<Boolean>> modify(@Validated(TenantForm.Modify.class) @RequestBody TenantForm form){
        return success(tenantEntityService.modify(form));
    }

    @PostMapping(value = "/audit")
    public ResponseEntity<Result<Boolean>> audit(@RequestBody TenantAuditForm form){
        return success(tenantEntityService.audit(form));
    }


}
