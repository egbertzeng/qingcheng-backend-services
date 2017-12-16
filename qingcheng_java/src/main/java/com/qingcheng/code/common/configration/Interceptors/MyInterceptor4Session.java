/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.common.configration.Interceptors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by liguohua on 16/7/17.
 */
@Configuration
public class MyInterceptor4Session extends HandlerInterceptorAdapter {
    private static final Logger logger = Logger.getLogger(MyInterceptor4Session.class);
    @Autowired
    private HttpSession session;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        //将数据存储到session中
        session.setAttribute("key1", "this is test data");
        //判断session是不是新创建的
        if (session.isNew()) {
            logger.info("session创建成功id是：" + session.getId() + "-->thread:" + Thread.currentThread());
        } else {
            logger.info("session已经存在id是：" + session.getId() + "-->thread:" + Thread.currentThread());
        }
        return true;
    }
}
