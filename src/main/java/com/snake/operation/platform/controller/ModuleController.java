package com.snake.operation.platform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.snake.operation.platform.model.dto.ModuleDTO;
import com.snake.operation.platform.model.form.ModuleForm;
import com.snake.operation.platform.model.queries.ModulePageEqualsQueries;
import com.snake.operation.platform.service.ModuleEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;
import io.github.yxsnake.pisces.web.core.base.Result;
import io.github.yxsnake.pisces.web.core.framework.controller.BaseController;
import io.github.yxsnake.pisces.web.core.framework.model.BaseFuzzyQueries;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
@Tag(name = "模块相关API")
@Slf4j
@RestController
@RequestMapping(value = "/module")
@RequiredArgsConstructor
public class ModuleController extends BaseController {

    private final ModuleEntityService moduleEntityService;


    @Operation(summary = "模块创建")
    @PostMapping(value = "/create")
    public ResponseEntity<Result<Boolean>> create(@Validated @RequestBody ModuleForm form){
        moduleEntityService.create(form);
        return success(Boolean.TRUE);
    }

    @Operation(summary = "模块修改")
    @PostMapping(value = "/modify")
    public ResponseEntity<Result<Boolean>> modify(@Validated @RequestBody ModuleForm form){
        moduleEntityService.modify(form);
        return success(Boolean.TRUE);
    }

    @Operation(summary = "分页查询模块列表")
    @PostMapping(value = "/pageList")
    public ResponseEntity<Result<IPage<ModuleDTO>>> pageList(@Validated @RequestBody QueryFilter<ModulePageEqualsQueries, BaseFuzzyQueries> queryFilter){
        return success(moduleEntityService.pageList(queryFilter));
    }

    @Operation(summary = "查询模块详情")
    @GetMapping(value = "/detail")
    public ResponseEntity<Result<ModuleDTO>> detail(@RequestParam("moduleId") String moduleId){
        return success(moduleEntityService.detail(moduleId));
    }

    @Operation(summary = "删除模块")
    @GetMapping(value = "/remove")
    public ResponseEntity<Result<Boolean>> remove(@RequestParam("moduleId") String moduleId){
        moduleEntityService.removeByModuleId(moduleId);
        return success(Boolean.TRUE);
    }

}
