/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.system.bigdata.zookeeper.bigdata.runninginfo;

import com.qingcheng.code.common.properties.AppDynamic;
import com.qingcheng.code.system.bigdata.zookeeper.service.ZookeeperUtil;

/**
 * Created by liguohua on  4/20/16.
 */
public class AlluxioRuningInfo {
    //1.Alluxio相关的属性
    private static boolean AlluxioCanEnble;
    private static String AlluxioactiveMasterUrl;

    static {
        try {
            //2.3#Alluxio相关的属性
            AlluxioactiveMasterUrl = ZookeeperUtil.getActiveAlluxioMasterUrl().trim();
            //2.4多媒体数据类型相应的属性
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 此方法用于,当zookeeper中的alluxio的master发生变化时,重新设置alluxio的master
     */
    public static void setAlluxioFilePathUrl(String activeAlluxioMasterUrl) {
        AlluxioactiveMasterUrl = activeAlluxioMasterUrl;
        setAlluxioIsEnable();
    }

    /*
     * 此方法用于,判断是否使用alluxio
     */
    private static void setAlluxioIsEnable() {
        //默认开启alluxio
        AlluxioCanEnble = true;
        if (AppDynamic.getQingchengConfBigdataAlluxioForceDisable()) {
            //如果,用户强制禁用alluxio,则关闭alluxio
            AlluxioCanEnble = false;
        }
        if (!HadoopRunningInfo.getCurrentRuningNamenode().equals(AppDynamic.getQingchengConfBigdataHadoopHdfsNamenodePresetHostname())) {
            //如果,alluxio的基础nameNode失败,则关闭alluxio
            AlluxioCanEnble = false;
        }
        if (AlluxioactiveMasterUrl == null || "".equals(AlluxioactiveMasterUrl)) {
            //如果,alluxio的master节点失败,则关闭alluxio
            AlluxioCanEnble = false;
        }
    }

    public static boolean isAlluxioCanEnble() {
        return AlluxioCanEnble;
    }

    public static String getAlluxioactiveMasterUrl() {
        return AlluxioactiveMasterUrl;
    }

}
