/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.system.bigdata.zookeeper.bigdata.runninginfo;

/**
 * Created by liguohua on 16/7/29.
 */

import com.qingcheng.code.system.bigdata.zookeeper.service.ZookeeperUtil;
import org.springframework.stereotype.Component;

@Component
public class HadoopRunningInfo {
    //Hadoop相关的属性
    private static String CurrentRuningNamenode;

    /**
     * 用于初始化Hadoop相关的静态属性
     */
    static {
        CurrentRuningNamenode = ZookeeperUtil.getActiveNamenodeHostname().trim();
    }

    public static String getCurrentRuningNamenode() {
        return CurrentRuningNamenode;
    }

    /**
     * 此方法用于,当zookeeper中的activeNamenode发生变化时,重新设置HDFS的activeNamenode
     */
    public static void setCurrentRuningNamenode(String currentRuningNamenode) {
        CurrentRuningNamenode = currentRuningNamenode;
        AlluxioRuningInfo.isAlluxioCanEnble();
    }

}