/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.system.bigdata.alluxio;

import com.qingcheng.code.common.properties.AppDynamic;
import com.qingcheng.code.common.util.advance.shell.RemoteShellExecutor;
import com.qingcheng.code.system.service.AllServiceManager;
import com.qingcheng.code.system.bigdata.zookeeper.bigdata.runninginfo.HadoopRunningInfo;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by liguohua on 16/8/21.
 */
public class AlluxioClusterService {
    private final static Logger logger = Logger.getLogger(AlluxioClusterService.class);

    /**
     * 根據 clusterJPS返回結果,分析本該啟動,但沒有啟動的zookeeper節點列表
     *
     * @param clusterJPSresult clusterJPS返回結果
     * @return 應該啟動但沒有啟動的zokeeper節點列表
     */
    public static List<String> getAlluxioShouldStartAllByClusterJPS(Map<String, List<String>> clusterJPSresult) {
        List<String> list = new LinkedList<>();
        list.add(AppDynamic.getQingchengConfBigdataAlluxioMasterHostname());
        return AllServiceManager.getShouldStartServiceHostList(clusterJPSresult, list, AlluxioContains.ALLUXIO_MASTER_DEAMON_NAME);
    }

    /**
     * alluxio cluster 启动集群
     *
     * @param masterHost 集群机器列表
     * @param username   用户名
     * @param password   密码
     */
    public static void alluxioClusterStartAllOnMasterHost(String masterHost, String username, String password) {
        if (noNeedToRestartAlluxioCluster(masterHost, "no need to start alluxio cluster!")) return;
        //1.格式化
        alluxioClusterFormateOnMasterHost(masterHost, username, password);
        //2.挂载并启动
        alluxioClusterStartAllAndMountOnMasterHost(masterHost, username, password);
        logger.info("alluxio cluster start sucess!");


    }

    private static boolean noNeedToRestartAlluxioCluster(String masterHost, String msg) {
        if (masterHost == null || "".equals(masterHost)) {
            logger.info(msg);
            return true;
        }
        if (!(AppDynamic.getQingchengConfBigdataHadoopHdfsNamenodePresetHostname().equalsIgnoreCase(HadoopRunningInfo.getCurrentRuningNamenode()))) {
            logger.info("alluxio required active namenode is " + AppDynamic.getQingchengConfBigdataHadoopHdfsNamenodePresetHostname() + ",but found active namenode is " + HadoopRunningInfo.getCurrentRuningNamenode());
            return true;
        }
        return false;
    }

    /**
     * alluxio cluster 停止集群
     *
     * @param masterHost 集群机器列表
     * @param username   用户名
     * @param password   密码
     */
    public static void alluxioClusterStopAllOnMasterHost(String masterHost, String username, String password) {
        if (noNeedToRestartAlluxioCluster(masterHost, "no need to stop alluxio cluster!")) return;
        String alluxionCmd = "${ALLUXION_HOME}/bin/alluxio-stop.sh  all";
        RemoteShellExecutor.executeShellCommandOnHostWhitResponse(masterHost, username, password, alluxionCmd);
        logger.info("alluxio cluster stop sucess!");

    }

    /**
     * alluxio cluster 启动并挂载存储
     *
     * @param masterHost 集群机器列表
     * @param username   用户名
     * @param password   密码
     */
    public static void alluxioClusterStartAllAndMountOnMasterHost(String masterHost, String username, String password) {
        String alluxionCmd = "${ALLUXION_HOME}/bin/alluxio-start.sh all Mount";
        RemoteShellExecutor.executeShellCommandOnHostWhitResponse(masterHost, username, password, alluxionCmd);
    }

    /**
     * alluxio cluster 格式化
     *
     * @param masterHost 集群机器列表
     * @param username   用户名
     * @param password   密码
     */
    public static void alluxioClusterFormateOnMasterHost(String masterHost, String username, String password) {
        String alluxionCmd = "${ALLUXION_HOME}/bin/alluxio format";
        RemoteShellExecutor.executeShellCommandOnHostWhitResponse(masterHost, username, password, alluxionCmd);
    }

    /**
     * alluxio cluster 通用执行方法
     *
     * @param masterHost 主节点集群名
     * @param username   用户名
     * @param password   密码
     * @param alluxioCmd 执行命令
     */
    private static void alluxioClusterExecutorOnMasterHost(String masterHost, String username, String password, String alluxioCmd) {
        RemoteShellExecutor.executeShellCommandOnHostWhitResponse(masterHost, username, password, alluxioCmd);
    }

    class AlluxioContains {
        public static final String ALLUXIO_MASTER_DEAMON_NAME = "AlluxioMaster";
    }
}
