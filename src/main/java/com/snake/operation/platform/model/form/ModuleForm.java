package com.snake.operation.platform.model.form;



import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import io.github.yxsnake.pisces.web.core.converter.Convert;

import java.io.Serializable;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description: 模块
 * @version: 1.0
 */
@Data
@Schema(description = "模块表单提交数据")
public class ModuleForm implements Convert, Serializable {

    @Schema(description = "模块唯一标识")
    @NotBlank(message = "模块唯一标识不能为空",groups = {Modify.class})
    private String moduleId;
    
    @Schema(description = "产品唯一标识")
    @NotBlank(message = "产品唯一标识不能为空",groups = {Create.class, Modify.class})
    private String productId;

    @Schema(description = "模块名称")
    @NotBlank(message = "模块名称不能为空",groups = {Create.class, Modify.class})
    private String moduleName;

    @Schema(description = "是否免费 (1:是，0:付费)")
    @NotNull(message = "是否免费不能为空",groups = {Create.class, Modify.class})
    private Integer isFree;

    public interface Create{}

    public interface Modify{}
}
