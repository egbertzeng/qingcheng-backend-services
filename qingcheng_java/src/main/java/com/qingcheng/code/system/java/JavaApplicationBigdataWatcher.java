/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.system.java;

import com.qingcheng.code.common.properties.AppDynamic;
import com.qingcheng.code.common.util.advance.shell.LocalShellExecutorBasicService;
import com.qingcheng.code.system.bigdata.zookeeper.dao.ZooKeeperWatcherBizDoerAdapter;
import com.qingcheng.code.system.bigdata.zookeeper.dao.ZookeeperDao;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created by liguohua on 16/8/28.
 */
public class JavaApplicationBigdataWatcher extends ZooKeeperWatcherBizDoerAdapter {
    private final static Logger logger = Logger.getLogger(JavaApplicationBigdataWatcher.class);

    @Override
    public void childAdded(PathChildrenCacheEvent event) {
        logicBIz();
    }

    @Override
    public void childRemoved(PathChildrenCacheEvent event) {
        logicBIz();
    }

    private void logicBIz() {
        List<String> znodes = ZookeeperDao.getChildZnodesForFullName(AppDynamic.getQingchengConfJavaApplicationClusterRegistZnodePath());
        String znode = znodes.get(0);
        logger.info(znode);
        //zk上注册的机器名称
        String znodeContent = ZookeeperDao.getZnodeContent(znode);
        logger.info(znodeContent);
        //获取本地机器名
        logger.info("hostNameInZK=" + znodeContent + ",hostNameInLocal=" + LocalShellExecutorBasicService.getLocalHostName());
        if (LocalShellExecutorBasicService.getLocalHostName().trim().equals(znodeContent.trim())) {
            AppDynamic.setThisJavaApplicationIsBigdataAndJavaclusterManager(true);
        } else {
            AppDynamic.setThisJavaApplicationIsBigdataAndJavaclusterManager(false);
        }
        logger.info("#### AppDynamic.isThisJavaApplicationIsBigdataAndJavaclusterManager() " + AppDynamic.isThisJavaApplicationIsBigdataAndJavaclusterManager());

    }
}
