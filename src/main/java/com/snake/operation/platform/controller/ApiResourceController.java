package com.snake.operation.platform.controller;

import com.snake.operation.platform.model.form.ApiResourceForm;
import com.snake.operation.platform.service.ApiResourceEntityService;
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
 * @description:
 * @version: 1.0
 */
@Tag(name = "租户相关API")
@Slf4j
@RestController
@RequestMapping(value = "/api-resource")
@RequiredArgsConstructor
public class ApiResourceController extends BaseController {

    private final ApiResourceEntityService apiResourceEntityService;

    @PostMapping(value = "/create")
    public ResponseEntity<Result<Boolean>> create(@Validated(ApiResourceForm.Create.class) @RequestBody ApiResourceForm form){
        apiResourceEntityService.create(form);
        return success(Boolean.TRUE);
    }

    @PostMapping(value = "/modify")
    public ResponseEntity<Result<Boolean>> modify(@Validated(ApiResourceForm.Modify.class) @RequestBody ApiResourceForm form){
        apiResourceEntityService.modify(form);
        return success(Boolean.TRUE);
    }

    @GetMapping(value = "/sync-tenant")
    public ResponseEntity<Result<Boolean>> syncTenant(@RequestParam("tenantId") String tenantId){
        return success(apiResourceEntityService.syncTenant(tenantId));
    }

}

