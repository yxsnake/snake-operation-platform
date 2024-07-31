package com.snake.operation.platform.controller;

import com.snake.operation.platform.model.dto.SysDeptDTO;
import com.snake.operation.platform.model.form.SysDeptForm;
import com.snake.operation.platform.service.SysDeptService;
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

import java.util.List;

@Tag(name = "运营平台部门")
@Slf4j
@RestController
@RequestMapping(value = "/sys-dept",produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class SysDeptController extends BaseController {

    private final SysDeptService sysDeptService;

    @Operation(summary = "创建部门")
    @PostMapping(value = "/create")
    public ResponseEntity<Result<Boolean>> create(@Validated(SysDeptForm.Create.class) @RequestBody SysDeptForm form){
        sysDeptService.create(form);
        return success(Boolean.TRUE);
    }

    @Operation(summary = "修改部门")
    @PostMapping(value = "/modify")
    public ResponseEntity<Result<Boolean>> modify(@Validated(SysDeptForm.Modify.class) @RequestBody SysDeptForm form){
        sysDeptService.modify(form);
        return success(Boolean.TRUE);
    }

    @Operation(summary = "查询部门详情")
    @GetMapping(value = "/detail")
    public ResponseEntity<Result<SysDeptDTO>> detail(@RequestParam("deptId") String deptId){
        return success(sysDeptService.detail(deptId));
    }

    @Operation(summary = "查询部门列表")
    @GetMapping(value = "/list-all")
    public ResponseEntity<Result<List<SysDeptDTO>>> listAll(){
        return success(sysDeptService.listAll());
    }
}
