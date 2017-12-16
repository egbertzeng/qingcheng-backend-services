/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.system.bigdata.zookeeper.service;

/**
 * Created by liguohua on 7/10/16.
 */

import com.google.protobuf.InvalidProtocolBufferException;
import com.qingcheng.code.system.bigdata.zookeeper.bigdata.runninginfo.AlluxioRuningInfo;
import com.qingcheng.code.common.properties.AppDynamic;
import com.qingcheng.code.common.properties.AppStatic;
import com.qingcheng.code.system.service.AllServiceManager;
import com.qingcheng.code.system.bigdata.zookeeper.bigdata.runninginfo.HadoopRunningInfo;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCache.StartMode;
import org.apache.curator.retry.RetryNTimes;
import org.apache.hadoop.hdfs.server.namenode.ha.proto.HAZKInfoProtos;
import org.apache.log4j.Logger;

import java.util.List;

public class ZookeeperUtil {
    private static final Logger LOGGER = Logger.getLogger(ZookeeperUtil.class);

    /**
     * 此方法用于从zookeeper中获取alluxio 的active master node
     *
     * @return
     */

    public static String getActiveAlluxioMasterUrl() {
        // 1.Connect to zk
        CuratorFramework client = CuratorFrameworkFactory.newClient(AppStatic.getZooKeeperClusterNodeAddress(), new RetryNTimes(AppStatic.getZookeeperClusterClientRetryTimes(), AppStatic.getZookeeperClusterClientTimeout()));
        client.start();
        String targetZNPname = AppDynamic.getQingchengConfBigdataAlluxioClusterRegistZnodePath();
        List<String> electionNodes = null;
        final String[] activeAlluxioMasterUrl = {""};
        try {
            electionNodes = client.getChildren().forPath(targetZNPname);
            if (electionNodes.size() == 0) {
                LOGGER.error("alluxio register none  znode , please restart alluxio to make sure on one master process alive!");
            } else if (electionNodes.size() == 1) {
                final String targetZNname = targetZNPname + "/" + electionNodes.get(0);
                final byte[] data0 = client.getData().forPath(targetZNname.trim());
                activeAlluxioMasterUrl[0] = new String(data0);
            } else if ((electionNodes.size() >= 2)) {
                LOGGER.warn("alluxio register too many znode , please restart alluxio to make sure on one master process alive!");
            }
            AlluxioRuningInfo.setAlluxioFilePathUrl(activeAlluxioMasterUrl[0]);
            LOGGER.info("init: activeAlluxioMasterUrl=" + activeAlluxioMasterUrl[0] + ",AlluxioIsEnble=" + AlluxioRuningInfo.isAlluxioCanEnble());
            // 2.Register watcher and cache data
            PathChildrenCache watcher = new PathChildrenCache(client, targetZNPname, true);
            watcher.getListenable().addListener((client0, event) -> {
                //zookeeper所监控的alluxio,发生动荡的时候,检测并启动大数据服务
                AllServiceManager.zookeeperWatherTrigerDetectBigdataApplication();
                ChildData data = event.getData();
                if (data != null) {
                    try {
                        activeAlluxioMasterUrl[0] = "";
                        activeAlluxioMasterUrl[0] = new String(client.getData().forPath(data.getPath().trim())).trim();
                        if (!"".equals(activeAlluxioMasterUrl[0])) {
                            //成功重新启动alluxio.等30秒,让alluxio准备就绪,然后再使用alluxio,可以改善用户体验
                            Thread.sleep(AppDynamic.getQingchengConfBigdataAlluxioRestartWaitTime());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        AlluxioRuningInfo.setAlluxioFilePathUrl(activeAlluxioMasterUrl[0]);
                        LOGGER.info("watcher: activeAlluxioMasterUrl=" + activeAlluxioMasterUrl[0] + ",AlluxioIsEnble=" + AlluxioRuningInfo.isAlluxioCanEnble());
                    }
                }
            });
            watcher.start(StartMode.BUILD_INITIAL_CACHE);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return activeAlluxioMasterUrl[0].trim();
    }

    /**
     * 此方法用于从zookeeper中获取hdfs 的active namenode
     *
     * @return
     * @throws Exception
     */
    public static String getActiveNamenodeHostname()  {
        try {
            // 1.Connect to zk
            CuratorFramework client = CuratorFrameworkFactory.newClient(AppStatic.getZooKeeperClusterNodeAddress(), new RetryNTimes(AppStatic.getZookeeperClusterClientRetryTimes(), AppStatic.getZookeeperClusterClientTimeout()));
            client.start();
            byte[] data0 = client.getData().forPath(AppDynamic.getQingchengConfBigdataHadoopClusterRegistZnodeFullPathSon());
            //activeNameNode初始值
            String initActiveNamenoedHostname = getActiveNamenodeHostnamestr(data0);
            // 2.Register watcher and cache data
            PathChildrenCache watcher = new PathChildrenCache(client, AppDynamic.getQingchengConfBigdataHadoopClusterRegistZnodeFullPathParent(), true);
            watcher.getListenable().addListener((client0, event) -> {
                //zookeeper所监控的nameNode,发生动荡的时候,检测并启动大数据服务
                AllServiceManager.zookeeperWatherTrigerDetectBigdataApplication();
                ChildData data = event.getData();
                if (data != null) {
                    byte[] data00 = data.getData();
                    if (data.getPath().equals(AppDynamic.getQingchengConfBigdataHadoopClusterRegistZnodeFullPathSon())) {
                        //activeNameNode更新值
                        HadoopRunningInfo.setCurrentRuningNamenode(getActiveNamenodeHostnamestr(data00));
                    }
                }
            });
            watcher.start(StartMode.BUILD_INITIAL_CACHE);
            return initActiveNamenoedHostname;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 此方法用于从zookeeper中的相应znode的内容中获取,需要的active namenode hostname
     *
     * @param znodeContent znode中的内容
     * @return znode内容中获取的active namenode hostname
     */
    private static String getActiveNamenodeHostnamestr(byte[] znodeContent) {
        HAZKInfoProtos.ActiveNodeInfo activeNodeInfo = null;
        String hostname = null;
        try {
            activeNodeInfo = HAZKInfoProtos.ActiveNodeInfo.parseFrom(znodeContent);
            hostname = activeNodeInfo.getHostname();
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        return hostname;
    }
}