/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.common.properties;

import com.qingcheng.code.common.constant.AppConstants;
import com.qingcheng.code.common.util.AppPropertiesReader;
import com.qingcheng.code.common.util.basic.str.AppStrUtils;

import java.util.List;
import java.util.Properties;

/**
 * Created by liguohua on 16/9/3.
 */
public class AppStatic {

    private static Properties LOCAL_STATIC_PROPERTIES = AppPropertiesReader.getLocalStaticProperties();


    public static String getServerUserName() {
        return LOCAL_STATIC_PROPERTIES.getProperty("qingcheng.conf.server.cluster.login.username").trim();
    }

    public static String getServerPassWord() {
        return LOCAL_STATIC_PROPERTIES.getProperty("qingcheng.conf.server.cluster.login.password").trim();
    }

    public static String getZookeeperClusterHostsStr() {
        return LOCAL_STATIC_PROPERTIES.getProperty("qingcheng.conf.bigdata.bigdata.zookeeper.cluster.ip.or.hostname").trim();
    }

    public static List<String> getZookeeperClusterHostsList() {
        return AppStrUtils.getHostListFromStringWithComma(getZookeeperClusterHostsStr());
    }

    public static String getZooKeeperClusterNodeAddress() {
        List<String> linkedList = getZookeeperClusterHostsList();
        StringBuilder sb = new StringBuilder();
        for (String e : linkedList) {
            sb.append((e.trim() + AppConstants.COLON + AppStatic.getZookeeperClusterPort() + AppConstants.COMMA).trim());
        }
        return sb.deleteCharAt(sb.length() - 1).toString().trim();
    }

    public static String getZookeeperClusterPort() {
        return LOCAL_STATIC_PROPERTIES.getProperty("qingcheng.conf.bigdata.zookeeper.cluster.port").trim();
    }

    public static Integer getZookeeperClusterClientTimeout() {
        return Integer.parseInt(LOCAL_STATIC_PROPERTIES.getProperty("qingcheng.conf.bigdata.zookeeper.cluster.client.timeout").trim());
    }

    public static Integer getZookeeperClusterClientRetryTimes() {
        return Integer.parseInt(LOCAL_STATIC_PROPERTIES.getProperty("qingcheng.conf.bigdata.zookeeper.cluster.client.retry.times").trim());
    }
}
