package com.snake.operation.platform.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.snake.operation.platform.model.dto.ProductDTO;
import com.snake.operation.platform.model.form.ProductForm;
import com.snake.operation.platform.model.queries.ProductPageEqualsQueries;
import com.snake.operation.platform.service.ProductEntityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.github.yxsnake.pisces.web.core.base.QueryFilter;
import io.github.yxsnake.pisces.web.core.base.Result;
import io.github.yxsnake.pisces.web.core.framework.controller.BaseController;
import io.github.yxsnake.pisces.web.core.framework.model.BaseFuzzyQueries;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
@Tag(name = "产品相关API")
@Slf4j
@RestController
@RequestMapping(value = "/product",consumes = MediaType.APPLICATION_JSON_VALUE,produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class ProductController extends BaseController {

    private final ProductEntityService productEntityService;

    @Operation(summary = "产品创建")
    @PostMapping(value = "/create")
    public ResponseEntity<Result<Boolean>> create(@Validated @RequestBody ProductForm form){
        productEntityService.create(form);
        return success(Boolean.TRUE);
    }

    @Operation(summary = "产品修改")
    @PostMapping(value = "/modify")
    public ResponseEntity<Result<Boolean>> modify(@Validated @RequestBody ProductForm form){
        productEntityService.modify(form);
        return success(Boolean.TRUE);
    }

    @Operation(summary = "分页查询产品列表")
    @PostMapping(value = "/page-list")
    public ResponseEntity<Result<IPage<ProductDTO>>> pageList(@Validated @RequestBody QueryFilter<ProductPageEqualsQueries, BaseFuzzyQueries> queryFilter){
        return success(productEntityService.pageList(queryFilter));
    }

    @Operation(summary = "查询产品详情")
    @GetMapping(value = "/detail")
    public ResponseEntity<Result<ProductDTO>> detail(@RequestParam("productId") String productId){
        return success(productEntityService.detail(productId));
    }


    @Operation(summary = "删除产品")
    @GetMapping(value = "/remove")
    public ResponseEntity<Result<Boolean>> remove(@RequestParam("productId") String productId){
        productEntityService.removeByProductId(productId);
        return success(Boolean.TRUE);
    }

}
