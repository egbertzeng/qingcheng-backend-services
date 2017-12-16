/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.system.bigdata.hadoop;

import com.qingcheng.code.common.properties.AppDynamic;
import com.qingcheng.code.common.properties.AppStatic;
import com.qingcheng.code.common.util.advance.shell.RemoteShellExecutor;
import com.qingcheng.code.system.service.AllServiceManager;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Map;

/**
 * Created by liguohua on 16/8/21.
 */
public class HadoopClusterService {
    private final static Logger logger = Logger.getLogger(HadoopClusterService.class);

    private static boolean sercrityCheck(String[] hosts, String msg) {
        if (hosts == null || hosts.length == 0) {
            logger.info(msg);
            return true;
        }
        return false;
    }

    /**
     * 根據 clusterJPS返回結果,分析本該啟動,但沒有啟動的ZKFC節點列表
     *
     * @param clusterJPSresult clusterJPS返回結果
     * @return 應該啟動但沒有啟動的ZKFC節點列表
     */
    public static List<String> getZKFCShouldStartListByClusterJPS(Map<String, List<String>> clusterJPSresult) {
        return AllServiceManager.getShouldStartServiceHostList(clusterJPSresult, AppDynamic.getQingchengConfBigdataHadoopHdfsNamenodeHostnameList(), HadoopConstains.HADOOP_DEAMON_ZKFC_NAME);
    }

    /**
     * 根據 clusterJPS返回結果,分析本該啟動,但沒有啟動的journalNode節點列表
     *
     * @param clusterJPSresult clusterJPS返回結果
     * @return 應該啟動但沒有啟動的journalNode節點列表
     */
    public static List<String> getJournalnodesShouldStartListByClusterJPS(Map<String, List<String>> clusterJPSresult) {
        return AllServiceManager.getShouldStartServiceHostList(clusterJPSresult, AppDynamic.getQingchengConfBigdataHadoopHdfsJournalnodeHostnameList(), HadoopConstains.HADOOP_DEAMON_JORUNALNODE_NAME);
    }

    /**
     * 根據 clusterJPS返回結果,分析本該啟動,但沒有啟動的dataNode節點列表
     *
     * @param clusterJPSresult clusterJPS返回結果
     * @return 應該啟動但沒有啟動的ndataNode節點列表
     */
    public static List<String> getDatanodesShouldStartListByClusterJPS(Map<String, List<String>> clusterJPSresult) {
        return AllServiceManager.getShouldStartServiceHostList(clusterJPSresult, AppDynamic.getQingchengConfBigdataHadoopHdfsDatanodeHostnameList(), HadoopConstains.HADOOP_DEAMON_DATANODE_NAME);
    }

    /**
     * 根據 clusterJPS返回結果,分析本該啟動,但沒有啟動的nameNode節點列表
     *
     * @param clusterJPSresult clusterJPS返回結果
     * @return 應該啟動但沒有啟動的nameNode節點列表
     */
    public static List<String> getNamenodesShouldStartListByClusterJPS(Map<String, List<String>> clusterJPSresult) {
        return AllServiceManager.getShouldStartServiceHostList(clusterJPSresult, AppDynamic.getQingchengConfBigdataHadoopHdfsNamenodeHostnameList(), HadoopConstains.HADOOP_DEAMON_NAMENODE_NAME);
    }

    /**
     * hadoop daemon 关闭Hadoop的HDFS
     *
     * @param hadoopNameNodes    namenode列表
     * @param hadoopDataNodes    datanode列表
     * @param hadoopJournalNodes journalnode列表
     * @param username           用户名
     * @param password           密码
     */

    public static void hadoopClusterStopHDFS(String[] hadoopNameNodes, String[] hadoopDataNodes, String[] hadoopJournalNodes, String username, String password) {
        //1..关闭datanode
        hadoopClusterStopDataNode(hadoopDataNodes, username, password);
        //2.关闭zkfc
        hadoopClusterStopZKFC(hadoopNameNodes, username, password);
        //3.关闭journalnode
        hadoopClusterStopJouranlNode(hadoopJournalNodes, username, password);
        //4.关闭namenode
        hadoopClusterStopNameNode(hadoopNameNodes, username, password);
        logger.info("hadoop hdfs cluster stop sucess!");
    }

    /**
     * hadoop daemon 启动Hadoop的HDFS
     *
     * @param hadoopNameNodes    namenode列表
     * @param hadoopDataNodes    datanode列表
     * @param hadoopJournalNodes journalnode列表
     * @param username           用户名
     * @param password           密码
     */

    public static void hadoopClusterStartHDFS(String[] hadoopNameNodes, String[] hadoopDataNodes, String[] hadoopJournalNodes, String username, String password) {
        //1.启动namenode
        hadoopClusterStartNameNode(hadoopNameNodes, username, password);
        //2.启动journalnode
        hadoopClusterStartJouranlNode(hadoopJournalNodes, username, password);
        //3.启动zkfc
        hadoopClusterStartZKFC(hadoopNameNodes, username, password);
        //4.启动datanode
        hadoopClusterStartDataNode(hadoopDataNodes, username, password);

    }

    /**
     * hadoop daemon 关闭ZKFC
     *
     * @param hadoopClusterHostsArray 集群机器列表
     * @param username                用户名
     * @param password                密码
     */

    public static void hadoopClusterStopZKFC(String[] hadoopClusterHostsArray, String username, String password) {
        if (sercrityCheck(hadoopClusterHostsArray, "no need to stop zkfc!")) return;
        hadoopClusterExecutor(hadoopClusterHostsArray, username, password, HadoopConstains.HADOOP_STOP_CMD + HadoopConstains.HADOOP_DEAMON_ZKFC);
    }

    /**
     * hadoop daemon 启动ZKFC
     *
     * @param hadoopClusterHostsArray 集群机器列表
     * @param username                用户名
     * @param password                密码
     */

    public static void hadoopClusterStartZKFC(String[] hadoopClusterHostsArray, String username, String password) {
        if (sercrityCheck(hadoopClusterHostsArray, "no need to start zkfc!")) return;
        hadoopClusterExecutor(hadoopClusterHostsArray, username, password, HadoopConstains.HADOOP_START_CMD + HadoopConstains.HADOOP_DEAMON_ZKFC);
    }

    /**
     * hadoop daemon 关闭datanode
     *
     * @param hadoopClusterHostsArray 集群机器列表
     * @param username                用户名
     * @param password                密码
     */

    public static void hadoopClusterStopDataNode(String[] hadoopClusterHostsArray, String username, String password) {
        if (sercrityCheck(hadoopClusterHostsArray, "no need to stop datanodes!")) return;
        hadoopClusterExecutor(hadoopClusterHostsArray, username, password, HadoopConstains.HADOOP_STOP_CMD + HadoopConstains.HADOOP_DEAMON_DATANODE);
    }

    /**
     * hadoop daemon 启动datanode
     *
     * @param hadoopClusterHostsArray 集群机器列表
     * @param username                用户名
     * @param password                密码
     */

    public static void hadoopClusterStartDataNode(String[] hadoopClusterHostsArray, String username, String password) {
        if (sercrityCheck(hadoopClusterHostsArray, "no need to start datanodes!")) return;
        hadoopClusterExecutor(hadoopClusterHostsArray, username, password, HadoopConstains.HADOOP_START_CMD + HadoopConstains.HADOOP_DEAMON_DATANODE);
    }

    /**
     * hadoop daemon 关闭namenod
     *
     * @param hadoopClusterHostsArray 集群机器列表
     * @param username                用户名
     * @param password                密码
     */

    public static void hadoopClusterStopNameNode(String[] hadoopClusterHostsArray, String username, String password) {
        if (sercrityCheck(hadoopClusterHostsArray, "no need to stop namenodes!")) return;
        hadoopClusterExecutor(hadoopClusterHostsArray, username, password, HadoopConstains.HADOOP_STOP_CMD + HadoopConstains.HADOOP_DEAMON_NAMENODE);
    }

    /**
     * hadoop daemon 启动namenod
     *
     * @param hadoopClusterHostsArray 集群机器列表
     */

    public static void hadoopClusterStartNameNode(String[] hadoopClusterHostsArray) {
        if (sercrityCheck(hadoopClusterHostsArray, "no need to start namenodes!")) return;
        hadoopClusterStartNameNode(hadoopClusterHostsArray, AppStatic.getServerUserName(), AppStatic.getServerPassWord());
    }

    /**
     * hadoop daemon 启动namenod
     *
     * @param hadoopClusterHostsArray 集群机器列表
     * @param username                用户名
     * @param password                密码
     */

    public static void hadoopClusterStartNameNode(String[] hadoopClusterHostsArray, String username, String password) {
        if (sercrityCheck(hadoopClusterHostsArray, "no need to start namenodes!")) return;
        hadoopClusterExecutor(hadoopClusterHostsArray, username, password, HadoopConstains.HADOOP_START_CMD + HadoopConstains.HADOOP_DEAMON_NAMENODE);
    }

    /**
     * hadoop daemon 关闭journalNode
     *
     * @param hadoopClusterHostsArray 集群机器列表
     * @param username                用户名
     * @param password                密码
     */

    public static void hadoopClusterStopJouranlNode(String[] hadoopClusterHostsArray, String username, String password) {
        if (sercrityCheck(hadoopClusterHostsArray, "no need to stop journalnodes!")) return;
        hadoopClusterExecutor(hadoopClusterHostsArray, username, password, HadoopConstains.HADOOP_STOP_CMD + HadoopConstains.HADOOP_DEAMON_JORUNALNODE);
    }

    /**
     * hadoop daemon 启动journalNode
     *
     * @param hadoopClusterHostsArray 集群机器列表
     * @param username                用户名
     * @param password                密码
     */

    public static void hadoopClusterStartJouranlNode(String[] hadoopClusterHostsArray, String username, String password) {
        if (sercrityCheck(hadoopClusterHostsArray, "no need to start journalnodes!")) return;
        hadoopClusterExecutor(hadoopClusterHostsArray, username, password, HadoopConstains.HADOOP_START_CMD + HadoopConstains.HADOOP_DEAMON_JORUNALNODE);
    }

    /**
     * hadoop daemon 通用执行方法
     *
     * @param hadoopClusterHostsArray 集群机器列表
     * @param username                用户名
     * @param password                密码
     * @param hadoopClusermangerCMD   执行命令
     */
    private static void hadoopClusterExecutor(String[] hadoopClusterHostsArray, String username, String password, String hadoopClusermangerCMD) {
        RemoteShellExecutor.executeShellCommandOnClusterWhiteResponse(hadoopClusterHostsArray, username, password, HadoopConstains.HADOOP_CLUSTER_hadoopdaemon_FILEPATH + hadoopClusermangerCMD);
    }

    /**
     *
     */
    public static String hadoopNameNodeStauts(String nameNodeHostName) {
        List<String> result = RemoteShellExecutor.executeShellCommandOnHostWhitResponse(nameNodeHostName, "$HADOOP_HOME/bin/hdfs  haadmin -getServiceState " + AppDynamic.getQingchengConfBigdataHadoopHdfsNamenodePresetHostname());
        return result.get(0);
    }

    class HadoopConstains {
        public static final String HADOOP_CLUSTER_hadoopdaemon_FILEPATH = "${HADOOP_HOME}/sbin/hadoop-daemon.sh";
        public static final String HADOOP_START_CMD = " start";
        public static final String HADOOP_STOP_CMD = " stop";
        public static final String HADOOP_DEAMON_JORUNALNODE = " journalnode";
        public static final String HADOOP_DEAMON_JORUNALNODE_NAME = "JournalNode";
        public static final String HADOOP_DEAMON_NAMENODE = " namenode";
        public static final String HADOOP_DEAMON_NAMENODE_NAME = "NameNode";
        public static final String HADOOP_DEAMON_DATANODE = " datanode";
        public static final String HADOOP_DEAMON_DATANODE_NAME = "DataNode";
        public static final String HADOOP_DEAMON_ZKFC = " zkfc";
        public static final String HADOOP_DEAMON_ZKFC_NAME = "DFSZKFailoverController";
    }
}
