package com.snake.operation.platform.utils;

import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: snake
 * @create-time: 2024-07-23
 * @description:
 * @version: 1.0
 */
public class RefreshTokenUtils {

    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private final static Map<String,String> refreshTokenMap = new ConcurrentHashMap<>();

    public static String generateRefreshToken(){
        // 随机数生成器
        SecureRandom random = new SecureRandom();

        // 生成一个32位长度的随机字符串
        StringBuilder sb = new StringBuilder(32);
        for (int i = 0; i < 32; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }

        String refreshToken = sb.toString();
        return refreshToken;
    }

    public static void setRefreshToken(String refreshToken,String userId){
        refreshTokenMap.put(refreshToken,userId);
    }

    public static String getUserId(String refreshToken){
        return refreshTokenMap.get(refreshToken);
    }

    public static void removeRefreshToken(String refreshToken){
        refreshTokenMap.remove(refreshToken);
    }


}
