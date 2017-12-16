/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.common.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by liguohua on 16/8/23.
 */
public class AppPropertiesReader {
    /**
     * 所有的属性文件
     */
    //1.独享的静态属性
    private static Properties LOCAL_STATIC_PROPERTIES = new Properties();
    //2.共享的动态属性
    private static Properties LOCAL_DYNAMIC_PROPERTIES = new Properties();
    private static Properties ZK_DYNAMIC_PROPERTIES = new Properties();

    static {
        try {
            LOCAL_STATIC_PROPERTIES = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/static.properties"));
            LOCAL_DYNAMIC_PROPERTIES = PropertiesLoaderUtils.loadProperties(new ClassPathResource("/dynamic.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Properties getLocalStaticProperties() {
        return LOCAL_STATIC_PROPERTIES;
    }

    public static Properties getLocalDynamicProperties() {
        return LOCAL_DYNAMIC_PROPERTIES;
    }

    public static Properties getZkDynamicProperties() {
        return ZK_DYNAMIC_PROPERTIES;
    }

}
