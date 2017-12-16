/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.common.configration;

import com.qingcheng.code.common.configration.Interceptors.MyInterceptor4CORS;
import com.qingcheng.code.common.configration.Interceptors.MyInterceptor4Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by liguohua on 16/7/17.
 */


@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter {
    //注入拦截器
    @Autowired
    private MyInterceptor4CORS myInterceptor4CORS;
    @Autowired
    private MyInterceptor4Session myInterceptor4Session;

    /**
     * 此方法用于,注册拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截所有跨域请求
        registry.addInterceptor(myInterceptor4CORS).addPathPatterns("/**");
        //拦截所有session
        registry.addInterceptor(myInterceptor4Session).addPathPatterns("/**").excludePathPatterns("/null");
    }
}