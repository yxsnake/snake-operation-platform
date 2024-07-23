package com.snake.operation.platform.model.enums;

import io.github.yxsnake.pisces.web.core.base.IBaseEnum;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum SysMenuTypeEnum implements IBaseEnum<Integer> {

    DIRECTORY(0,"目录"),

    MENU(1,"菜单"),

    BUTTON(2,"按钮"),

    ;
    private final Integer value;

    private final String label;

    SysMenuTypeEnum(final Integer value,final String label){
        this.value = value;
        this.label = label;
    }

    public static SysMenuTypeEnum getInstance(final Integer value){
        return Arrays.asList(values()).stream().filter(item->item.getValue().equals(value)).findFirst().orElse(null);
    }
}
