package com.snake.operation.platform.model.enums;

import lombok.Getter;
import io.github.yxsnake.pisces.web.core.base.IBaseEnum;

import java.util.Arrays;

/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
@Getter
public enum FlagFreeEnum implements IBaseEnum<Integer> {

    PAY(1, "付费"),
    FREE(0, "免费");

    private final Integer value;
    private final String label;

    private FlagFreeEnum(final Integer value, final String label) {
        this.value = value;
        this.label = label;
    }

    public static FlagFreeEnum getInstance(final Integer value) {
        return Arrays.asList(values()).stream().filter((item) -> {
            return item.getValue().equals(value);
        }).findFirst().orElse(null);
    }
}
