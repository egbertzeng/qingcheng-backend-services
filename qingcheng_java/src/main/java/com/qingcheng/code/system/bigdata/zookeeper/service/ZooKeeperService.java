/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.system.bigdata.zookeeper.service;

import com.qingcheng.code.common.properties.AppStatic;
import com.qingcheng.code.common.util.advance.shell.RemoteShellExecutor;
import com.qingcheng.code.common.util.basic.str.AppStrUtils;
import com.qingcheng.code.system.host.HostClusterService;
import com.qingcheng.code.system.service.AllServiceManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;


/**
 * Created by liguohua on 16/8/21.
 */

public class ZooKeeperService {
    private final static Logger logger = Logger.getLogger(ZooKeeperService.class);

    /**
     * 初始化zookeeper集群
     */
    public static void strartZookeeperCluster() {
        Map<String, List<String>> result = HostClusterService.clusterJPS(AppStatic.getZookeeperClusterHostsList(), AppStatic.getServerUserName(), AppStatic.getServerPassWord());
        List<String> zknodes = ZooKeeperService.getZookeeperShouldStartListByClusterJPS(result);
        String[] zks = AppStrUtils.getStringArrayFromList(zknodes);
        ZooKeeperService.zookeeperClusterStart(zks, AppStatic.getServerUserName(), AppStatic.getServerPassWord());
        ZooKeeperService.zookeeperClusterStatus(zks, AppStatic.getServerUserName(), AppStatic.getServerPassWord());
    }


    /**
     * 检测集群操作的安全性
     *
     * @param hosts 集群
     * @param msg   消息
     * @return 是否安全
     */
    private static boolean noNeedOperateZookeeperCluster(String[] hosts, String msg) {
        if (hosts == null || hosts.length == 0) {
            logger.info(msg);
            return true;
        }
        return false;
    }

    /**
     * 根據 clusterJPS返回結果,分析本該啟動,但沒有啟動的zookeeper節點列表
     *
     * @param clusterJPSresult clusterJPS返回結果
     * @return 應該啟動但沒有啟動的zokeeper節點列表
     */
    public static List<String> getZookeeperShouldStartListByClusterJPS(Map<String, List<String>> clusterJPSresult) {
        return AllServiceManager.getShouldStartServiceHostList(clusterJPSresult, AppStatic.getZookeeperClusterHostsList(), ZookeeperConstains.ZOOKEEPER_DEAMON_NAME);
    }

    /**
     * 启动zookeeper集群
     *
     * @param zooKeeperClusterHostsArray
     * @param username
     * @param password
     */

    public static void zookeeperClusterStart(String[] zooKeeperClusterHostsArray, String username, String password) {
        if (noNeedOperateZookeeperCluster(zooKeeperClusterHostsArray, "no need to start zookeeper cluster!")) return;
        zookeeperClusterExecutor(zooKeeperClusterHostsArray, username, password, ZookeeperConstains.ZOOKEEPER_START_CMD);
        logger.info("zookeeper cluster start sucess!");

    }

    /**
     * 查看zookeeper集群的运行状态
     *
     * @param zooKeeperClusterHostsArray
     * @param username
     * @param password
     */
    public static void zookeeperClusterStatus(String[] zooKeeperClusterHostsArray, String username, String password) {
        if (noNeedOperateZookeeperCluster(zooKeeperClusterHostsArray, "no need to status zookeeper cluster!")) return;
        zookeeperClusterExecutor(zooKeeperClusterHostsArray, username, password, ZookeeperConstains.ZOOKEEPER_STATUS_CMD);
        logger.info("zookeeper cluster status sucess!");
    }

    /**
     * 停止zookeeper集群
     *
     * @param zooKeeperClusterHostsArray
     * @param username
     * @param password
     */
    public static void zookeeperClusterStop(String[] zooKeeperClusterHostsArray, String username, String password) {
        if (noNeedOperateZookeeperCluster(zooKeeperClusterHostsArray, "no need to stop zookeeper cluster!")) return;
        zookeeperClusterExecutor(zooKeeperClusterHostsArray, username, password, ZookeeperConstains.ZOOKEEPER_STOP_CMD);
        logger.info("zookeeper cluster stop sucess!");

    }

    /**
     * 重新启动zookeeper集群
     *
     * @param zooKeeperClusterHostsArray
     * @param username
     * @param password
     */
    public static void zookeeperClusterRestart(String[] zooKeeperClusterHostsArray, String username, String password) {
        if (noNeedOperateZookeeperCluster(zooKeeperClusterHostsArray, "no need to restart zookeeper cluster!")) return;
        zookeeperClusterExecutor(zooKeeperClusterHostsArray, username, password, ZookeeperConstains.ZOOKEEPER_RESTART_CMD);
        logger.info("zookeeper cluster restart sucess!");
    }

    /**
     * zookeeper集群管理的通用执行器
     *
     * @param zooKeeperClusterHostsArray
     * @param username
     * @param password
     * @param zookeeperClusermangerCMD
     */
    private static void zookeeperClusterExecutor(String[] zooKeeperClusterHostsArray, String username, String password, String zookeeperClusermangerCMD) {
        //遍历集群列表,对每个集群执行相应的操作
        RemoteShellExecutor.executeShellCommandOnClusterWhiteResponse(zooKeeperClusterHostsArray, username, password, ZookeeperConstains.ZOOKEEPER_CLUSTER_zkServer__FILEPATH + zookeeperClusermangerCMD);
    }

    class ZookeeperConstains {
        public static final String ZOOKEEPER_CLUSTER_zkServer__FILEPATH = "${ZOOKEEPER_HOME}/bin/zkServer.sh";
        public static final String ZOOKEEPER_DEAMON_NAME = "QuorumPeerMain";
        public static final String ZOOKEEPER_START_CMD = " start";
        public static final String ZOOKEEPER_STATUS_CMD = " status";
        public static final String ZOOKEEPER_STOP_CMD = " stop";
        public static final String ZOOKEEPER_RESTART_CMD = " restart";
    }


}
