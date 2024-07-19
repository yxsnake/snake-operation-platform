package com.snake.operation.platform.model.enums;

import io.github.yxsnake.pisces.web.core.base.IBaseEnum;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author: snake
 * @create-time: 2024-07-19
 * @description:
 * @version: 1.0
 */
@Getter
public enum TenantAuditStatusEnum implements IBaseEnum<Integer> {

    TODO(0,"待审核"),

    PASS(1,"通过"),

    REJECT(2,"驳回"),

    ;

    private final Integer value;
    private final String label;

    private TenantAuditStatusEnum(final Integer value, final String label) {
        this.value = value;
        this.label = label;
    }

    public static TenantAuditStatusEnum getInstance(final Integer value) {
        return Arrays.asList(values()).stream().filter((item) -> item.getValue().equals(value)).findFirst().orElse(null);
    }
}
