package com.snake.operation.platform.retrofit.client;

import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
import com.snake.operation.platform.retrofit.model.form.PlatformInitTenantForm;
import io.github.yxsnake.pisces.web.core.base.Result;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author: snake
 * @create-time: 2024-07-19
 * @description:
 * @version: 1.0
 */
@RetrofitClient(baseUrl = "${base.url.snake-system-server}")
public interface TenantClient {

    /**
     * 初始化租户
     * @param form
     * @return
     */
    @POST(value = "tenant/initTenant")
    Result<Boolean> initTenant(@Body PlatformInitTenantForm form);
}
