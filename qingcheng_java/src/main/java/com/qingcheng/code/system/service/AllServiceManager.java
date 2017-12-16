/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.system.service;

import com.qingcheng.code.common.constant.AppConstants;
import com.qingcheng.code.common.properties.AppDynamic;
import com.qingcheng.code.common.properties.AppStatic;
import com.qingcheng.code.common.util.advance.media.GlobalFlags;
import com.qingcheng.code.common.util.basic.str.AppStrUtils;
import com.qingcheng.code.system.bigdata.alluxio.AlluxioClusterService;
import com.qingcheng.code.system.bigdata.hadoop.HadoopClusterService;
import com.qingcheng.code.system.bigdata.zookeeper.service.ZooKeeperService;
import com.qingcheng.code.system.host.HostClusterService;
import com.qingcheng.code.system.java.JavaApplicationService;

import java.util.*;

/**
 * Created by liguohua on 16/8/21.
 */
public class AllServiceManager {
    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ZooKeeperService.class);
    private static Integer count = 0;

    /**
     * before java once detection,此方法用于在Java application 启动前循环检测,并启动大数据服务
     */
    public static void preStartJavaApplicationDetectBigdataApplication() {
        if (AppDynamic.getQingchengConfJavaApplicationDetectorForBigdataBeforeOnceDetectorIsEnable().equalsIgnoreCase(AppConstants.LOWER_CASE_FALSE_STR)) {
            return;
        }
        //此Java程序不是大数据集群的监控者
        if (!AppDynamic.isThisJavaApplicationIsBigdataAndJavaclusterManager()) {
            logger.info("初始化时期的一次判定:经判定此java程序,目前还不是bigdata的守护者,THIS_JAVA_APPLICATION_IS_BIGDATA_AND_JAVACLUSTER_MANAGER=" + AppDynamic.isThisJavaApplicationIsBigdataAndJavaclusterManager());
            return;
        }
        AllServiceManager.jpsDetectAndStartAllBigdataApplicationService(AppStatic.getServerUserName(), AppStatic.getServerPassWord());
    }


    /**
     * running java loop detection,此方法用于在Java application 启动后循环检测,并启动大数据服务
     */
    public static void posStartJavaApplicationDetectBigdataApplication() {
        if (!AppDynamic.getQingchengConfJavaApplicationDetectorForBigdataLoopDetectorIsEnable().equalsIgnoreCase(AppConstants.LOWER_CASE_TRUE_STR)) {
            return;
        }
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                    try {
                        Thread.sleep(AppDynamic.getQingchengConfJavaApplicationDetectorForBigdataLoopDetectorIntervalTime());
                        //此Java程序不是大数据集群的监控者
                        if (!AppDynamic.isThisJavaApplicationIsBigdataAndJavaclusterManager()) {
                            logger.info("轮询判定:经判定此java程序,目前还不是bigdata的守护者,THIS_JAVA_APPLICATION_IS_BIGDATA_AND_JAVACLUSTER_MANAGER=" + AppDynamic.isThisJavaApplicationIsBigdataAndJavaclusterManager());
                            continue;
                        }
                        AllServiceManager.jpsDetectAndStartAllBigdataApplicationService(AppStatic.getServerUserName(), AppStatic.getServerPassWord());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.setDaemon(true);
        t1.start();
    }

    /**
     * running java loop detection,此方法用于在Java application 启动后循环检测,并启动java cluster
     */
    public static void posStartJavaApplicationDetectJavaClusters() {

        if (AppDynamic.getQingchengConfJavaApplicationDetectorForJavaLoopDetectorIsEnable().equalsIgnoreCase(AppConstants.LOWER_CASE_FALSE_STR)) {
            return;
        }
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        //1.休息
                        Thread.sleep(AppDynamic.getQingchengConfJavaApplicationDetectorForJavaLoopDetectorrIntervalTime());
                        //2.判定:此Java程序不是监控者
                        if (!AppDynamic.isThisJavaApplicationIsBigdataAndJavaclusterManager()) {
                            logger.info("轮询判定:经判定此java程序,目前还不是java守护者,THIS_JAVA_APPLICATION_IS_BIGDATA_AND_JAVACLUSTER_MANAGER=" + AppDynamic.isThisJavaApplicationIsBigdataAndJavaclusterManager());
                            continue;
                        }
                        //3.将jar文件复制到目标主机上
                        JavaApplicationService.copyJavaJarFileToShouldHaveJarFileHosts();
                        //4.启动
                        JavaApplicationService.startShouldStartJavaApplicationHostList();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        t1.setDaemon(true);
        t1.start();
    }

    /**
     * change znode random detection,此方法用于在Java application运行中,发现zookeeper所的znode动荡,检测并启动大数据服务
     */
    public static void zookeeperWatherTrigerDetectBigdataApplication() {
        //此Java程序不是大数据集群的监控者
        if (!AppDynamic.isThisJavaApplicationIsBigdataAndJavaclusterManager()) {
            logger.info("zk动荡随机判定:经判定此java程序,目前还不是守护者,THIS_JAVA_APPLICATION_IS_BIGDATA_AND_JAVACLUSTER_MANAGER=" + AppDynamic.isThisJavaApplicationIsBigdataAndJavaclusterManager());
            return;
        }
        if (AppDynamic.getQingchengConfJavaApplicationDetectorForBigdataZnodeDatachangeRandomDetectorIsEnable().equalsIgnoreCase(AppConstants.LOWER_CASE_FALSE_STR)) {
            return;
        }
        try {
            Thread.sleep(AppDynamic.getQingchengConfJavaApplicationDetectorForBigdataZnodeDatachangeRandomDetectorWaitTime());
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    AllServiceManager.jpsDetectAndStartAllBigdataApplicationService(AppStatic.getServerUserName(), AppStatic.getServerPassWord());
                }
            });
            thread.setDaemon(true);
            thread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    /**
     * 集群中可以停止服务的机器列表
     *
     * @param result                返回结果
     * @param propertiesServiceList 检测的服务列表
     * @param serviceName           检测的服务名称
     * @return 应当停止服务的机器列表
     */
    public static List<String> canbeStopServiceHostList(Map<String, List<String>> result, List<String> propertiesServiceList, String serviceName) {
        return isServiceShouldStartAndCanbeStop(result, propertiesServiceList, serviceName).get(1);
    }

    /**
     * 集群中需要启动服务的机器列表
     *
     * @param result                返回结果
     * @param propertiesServiceList 检测的服务列表
     * @param serviceName           检测的服务名称
     * @return 应当启动服务的机器列表
     */
    public static List<String> getShouldStartServiceHostList(Map<String, List<String>> result, List<String> propertiesServiceList, String serviceName) {
        return isServiceShouldStartAndCanbeStop(result, propertiesServiceList, serviceName).get(0);
    }

    /**
     * 集群中需要启动服务的机器列表和集群中可以停止服务的机器列表
     *
     * @param result                返回结果
     * @param propertiesServiceList 检测的服务列表
     * @param serviceName           检测的服务名称
     * @return 集群中需要启动服务的机器列表和集群中可以停止服务的机器列表
     */
    private static List<List<String>> isServiceShouldStartAndCanbeStop(Map<String, List<String>> result, List<String> propertiesServiceList, String serviceName) {
        List<List<String>> all = new ArrayList<>();
        List<String> shouldStartServiceHostsList = new LinkedList<>();
        List<String> canbeStopServiceHostsList = new LinkedList<>();
        for (String host : propertiesServiceList) {
            boolean isServiceShouldStart = true;
            boolean isServiceCanbeStop = false;
            List<String> oneHostOutPut = result.get(host);
            for (String s : oneHostOutPut) {
                if (s.contains(serviceName)) {
                    isServiceShouldStart = false;
                    isServiceCanbeStop = true;
                }
            }
            //应当被启动的服务
            if (isServiceShouldStart && !shouldStartServiceHostsList.contains(host)) {
                shouldStartServiceHostsList.add(host);
            }
            //可以被停止的服务
            if (isServiceCanbeStop && !canbeStopServiceHostsList.contains(host)) {
                canbeStopServiceHostsList.add(host);
            }
        }
        all.add(shouldStartServiceHostsList);
        all.add(canbeStopServiceHostsList);
        return all;
    }

    /**
     * 根據集群的JPS信息,確定是否啟動相應的服務
     */
    public static void jpsDetectAndStartAllBigdataApplicationService(String username, String password) {
        List<String> allHostsDistinck = collectAllHost(AppStatic.getZookeeperClusterHostsList(), AppDynamic.getQingchengConfBigdataHadoopHdfsNamenodeHostnameList(), AppDynamic.getQingchengConfBigdataHadoopHdfsDatanodeHostnameList(), AppDynamic.getQingchengConfBigdataHadoopHdfsJournalnodeHostnameList(), AppDynamic.getQingchengConfBigdataAlluxioMasterHostname());
        Map<String, List<String>> result = HostClusterService.clusterJPS(allHostsDistinck, username, password);
        List<String> zknodes = ZooKeeperService.getZookeeperShouldStartListByClusterJPS(result);
        logger.info("should start zookeeper nodes= " + zknodes.toString());
        List<String> namenodes = HadoopClusterService.getNamenodesShouldStartListByClusterJPS(result);
        logger.info("should start hadoop namenodes= " + namenodes.toString());
        List<String> datanodes = HadoopClusterService.getDatanodesShouldStartListByClusterJPS(result);
        logger.info("should start hadoop datanodes= " + datanodes.toString());
        List<String> journalnodes = HadoopClusterService.getJournalnodesShouldStartListByClusterJPS(result);
        logger.info("should start hadoop journalnodes= " + journalnodes.toString());
        List<String> zkfs = HadoopClusterService.getZKFCShouldStartListByClusterJPS(result);
        logger.info("should start hadoop zkfcnodes= " + zkfs.toString());
        List<String> alluxio = AlluxioClusterService.getAlluxioShouldStartAllByClusterJPS(result);
        String alluxioMaster = "";
        if (alluxio.size() > 0) {
            alluxioMaster = alluxio.get(0);
        }
        logger.info("should start alluxio master nodes= " + alluxioMaster.toString());

        startAllService(zknodes, namenodes, datanodes, journalnodes, alluxioMaster, username, password);

    }

    /**
     * 所有服务发启动入口程序
     *
     * @param zooKeeperClusterNodes zookeeper集群的所有节点
     * @param hadoopNameNodes       hdfs的namenode
     * @param hadoopDataNodes       hdfs的datanode
     * @param hadoopJournalNodes    hdfs的journalnode
     * @param alluxioMaster         alluxio的主节点
     * @param username              用户名
     * @param password              密码
     */
    public static void startAllService(List<String> zooKeeperClusterNodes, List<String> hadoopNameNodes, List<String> hadoopDataNodes, List<String> hadoopJournalNodes, String alluxioMaster, String username, String password) {
        String[] zks = AppStrUtils.getStringArrayFromList(zooKeeperClusterNodes);
        String[] names = AppStrUtils.getStringArrayFromList(hadoopNameNodes);
        String[] datas = AppStrUtils.getStringArrayFromList(hadoopDataNodes);
        String[] jornals = AppStrUtils.getStringArrayFromList(hadoopJournalNodes);
        startAllBigdataService(zks, names, datas, jornals, alluxioMaster, username, password);
    }

    /**
     * 所有服务发启动入口程序
     *
     * @param zooKeeperClusterNodes zookeeper集群的所有节点
     * @param hadoopNameNodes       hdfs的namenode
     * @param hadoopDataNodes       hdfs的datanode
     * @param hadoopJournalNodes    hdfs的journalnode
     * @param alluxioMaster         alluxio的主节点
     * @param username              用户名
     * @param password              密码
     */
    public static void startAllBigdataService(String[] zooKeeperClusterNodes, String[] hadoopNameNodes, String[] hadoopDataNodes, String[] hadoopJournalNodes, String alluxioMaster, String username, String password) {
        //1.启动zookeeper
        ZooKeeperService.zookeeperClusterStart(zooKeeperClusterNodes, username, password);
        ZooKeeperService.zookeeperClusterStatus(zooKeeperClusterNodes, username, password);
        //2.启动hdfs
        HadoopClusterService.hadoopClusterStartHDFS(hadoopNameNodes, hadoopDataNodes, hadoopJournalNodes, username, password);
        //3.启动alluxio
        if (GlobalFlags.IS_First_Time_RUN) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    GlobalFlags.IS_First_Time_RUN = false;
                    try {
                        Thread.sleep(AppDynamic.getQingchengConfJavaApplicationDetectorForBigdataBeforeOnceDetectorAlluxioWaitTime());//首次启动,需要等待HDF準備就緒,再启动ALLUXIO
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    AlluxioClusterService.alluxioClusterStartAllOnMasterHost(alluxioMaster, username, password);
                }
            });
            thread.setDaemon(true);
            thread.start();
        } else {
            AlluxioClusterService.alluxioClusterStartAllOnMasterHost(alluxioMaster, username, password);
        }
        //4.反馈报告
        HostClusterService.clusterJPS(collectAllHost(zooKeeperClusterNodes, hadoopNameNodes, hadoopDataNodes, hadoopJournalNodes, alluxioMaster), username, password);
    }

    /**
     * 所有服务发关闭入口
     *
     * @param zooKeeperClusterNodes zookeeper集群的所有节点
     * @param hadoopNameNodes       hdfs的namenode
     * @param hadoopDataNodes       hdfs的datanode
     * @param hadoopJournalNodes    hdfs的journalnode
     * @param alluxioMaster         alluxio的主节点
     * @param username              用户名
     * @param password              密码
     */
    public static void stopAllBigdataService2(String[] zooKeeperClusterNodes, String[] hadoopNameNodes, String[] hadoopDataNodes, String[] hadoopJournalNodes, String alluxioMaster, String username, String password) {
        //3.关闭zookeeper
        ZooKeeperService.zookeeperClusterStop(zooKeeperClusterNodes, username, password);
        //1.关闭alluxio
        AlluxioClusterService.alluxioClusterStopAllOnMasterHost(alluxioMaster, username, password);
        //2.关闭hdfs
        HadoopClusterService.hadoopClusterStopHDFS(hadoopNameNodes, hadoopDataNodes, hadoopJournalNodes, username, password);
        //4.反馈
        HostClusterService.clusterJPS(collectAllHost(zooKeeperClusterNodes, hadoopNameNodes, hadoopDataNodes, hadoopJournalNodes, alluxioMaster), username, password);
    }

    /**
     * 所有服务发关闭入口
     *
     * @param zooKeeperClusterNodes zookeeper集群的所有节点
     * @param hadoopNameNodes       hdfs的namenode
     * @param hadoopDataNodes       hdfs的datanode
     * @param hadoopJournalNodes    hdfs的journalnode
     * @param alluxioMaster         alluxio的主节点
     * @param username              用户名
     * @param password              密码
     */
    public static void stopAllBigdataService(String[] zooKeeperClusterNodes, String[] hadoopNameNodes, String[] hadoopDataNodes, String[] hadoopJournalNodes, String alluxioMaster, String username, String password) {
        //1.关闭alluxio
        AlluxioClusterService.alluxioClusterStopAllOnMasterHost(alluxioMaster, username, password);
        //2.关闭hdfs
        HadoopClusterService.hadoopClusterStopHDFS(hadoopNameNodes, hadoopDataNodes, hadoopJournalNodes, username, password);
        //3.关闭zookeeper
        ZooKeeperService.zookeeperClusterStop(zooKeeperClusterNodes, username, password);
        //4.反馈
        HostClusterService.clusterJPS(collectAllHost(zooKeeperClusterNodes, hadoopNameNodes, hadoopDataNodes, hadoopJournalNodes, alluxioMaster), username, password);
    }

    /**
     * 收集所有集群列表信息,并通过set去重
     *
     * @param zooKeeperClusterNodes zookeeper集群的所有节点
     * @param hadoopNameNodes       hdfs的namenode
     * @param hadoopDataNodes       hdfs的datanode
     * @param hadoopJournalNodes    hdfs的journalnode
     * @param alluxioMaster         alluxio的主节点
     * @return 集群中所有机器无重列表
     */
    private static List<String> collectAllHost(String[] zooKeeperClusterNodes, String[] hadoopNameNodes, String[] hadoopDataNodes, String[] hadoopJournalNodes, String alluxioMaster) {
        return collectAllHost(Arrays.asList(zooKeeperClusterNodes), Arrays.asList(hadoopNameNodes), Arrays.asList(hadoopDataNodes), Arrays.asList(hadoopJournalNodes), alluxioMaster);
    }

    /**
     * 收集所有集群列表信息,并通过set去重
     *
     * @param zooKeeperClusterNodes zookeeper集群的所有节点
     * @param hadoopNameNodes       hdfs的namenode
     * @param hadoopDataNodes       hdfs的datanode
     * @param hadoopJournalNodes    hdfs的journalnode
     * @param alluxioMaster         alluxio的主节点
     * @return 集群中所有机器无重列表
     */
    private static List<String> collectAllHost(List<String> zooKeeperClusterNodes, List<String> hadoopNameNodes, List<String> hadoopDataNodes, List<String> hadoopJournalNodes, String alluxioMaster) {
        List<String> allHosts = new LinkedList<>();
        for (String s : zooKeeperClusterNodes) {
            if (!allHosts.contains(s)) {
                allHosts.add(s);
            }
        }
        for (String s : hadoopNameNodes) {
            if (!allHosts.contains(s)) {
                allHosts.add(s);
            }
        }
        for (String s : hadoopDataNodes) {
            if (!allHosts.contains(s)) {
                allHosts.add(s);
            }
        }
        for (String s : hadoopJournalNodes) {
            if (!allHosts.contains(s)) {
                allHosts.add(s);
            }
        }
        String s = alluxioMaster;
        if (!allHosts.contains(s)) {
            allHosts.add(s);
        }
        return allHosts;
    }

}
