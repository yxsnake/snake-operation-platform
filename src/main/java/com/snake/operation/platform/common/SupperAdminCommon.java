package com.snake.operation.platform.common;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;


/**
 * @author: snake
 * @create-time: 2024-07-16
 * @description:
 * @version: 1.0
 */
public class SupperAdminCommon {

    public static String getSupperAdminAccount(){
        String randomStr = RandomUtil.randomNumbers(10);
        return DateUtil.format(DateUtil.date(), DatePattern.NORM_YEAR_PATTERN)+randomStr;
    }

    public static void main(String[] args) {
        String supperAdminAccount = getSupperAdminAccount();
        System.out.println(supperAdminAccount);
    }
}
