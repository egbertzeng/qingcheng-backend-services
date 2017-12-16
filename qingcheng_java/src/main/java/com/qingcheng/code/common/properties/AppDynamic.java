/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.common.properties;

import com.qingcheng.code.common.util.AppPropertiesReader;
import com.qingcheng.code.common.util.basic.str.AppStrUtils;
import com.qingcheng.code.system.bigdata.zookeeper.dao.ZooKeeperWatcherBizDoerAdapter;
import com.qingcheng.code.system.bigdata.zookeeper.dao.ZookeeperDao;
import org.apache.curator.framework.recipes.cache.NodeCache;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;

/**
 * Created by liguohua on 16/9/3.
 */
public class AppDynamic {
    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AppDynamic.class);

    /**
     * 2.以下是属性文件的获取,和同步
     */
    private final static AppDynamicWatcher appDynamicWatcher = new AppDynamicWatcher();
    private final static Properties LOCAL_DYNAMIC_PROPERTIES = AppPropertiesReader.getLocalDynamicProperties();
    private final static Properties ZK_DYNAMIC_PROPERTIES = AppPropertiesReader.getZkDynamicProperties();
    //初始化ZK_DYNAMIC_PROPERTIES
    static {
        Set<Object> keys = LOCAL_DYNAMIC_PROPERTIES.keySet();
        for (Object k:keys) {
            ZK_DYNAMIC_PROPERTIES.put(AppStrUtils.convertPropertyFieldToZnodePath((String) k),LOCAL_DYNAMIC_PROPERTIES.get(k));
        }
    }
    /**
     * 3.以下是所有Java程序共享的变量,在zookeeper上註冊的動態屬性
     */

    private final static String QINGCHENG_CONF_BIGDATA_HADOOP_HDFS_JOURNALNODE_HOSTNAME = "qingcheng.conf.bigdata.hadoop.hdfs.journalnode.hostname".trim();
    private final static String QINGCHENG_CONF_BIGDATA_HADOOP_HDFS_NAMENODE_HOSTNAME = "qingcheng.conf.bigdata.hadoop.hdfs.namenode.hostname".trim();
    private final static String QINGCHENG_CONF_BIGDATA_HADOOP_CLUSTER_REGIST_ZNODE_PATH = "qingcheng.conf.bigdata.hadoop.cluster.regist.znode.path".trim();
    private final static String QINGCHENG_CONF_BIGDATA_HADOOP_HDFS_DATANODE_HOSTNAME = "qingcheng.conf.bigdata.hadoop.hdfs.datanode.hostname".trim();
    private final static String QINGCHENG_CONF_BIGDATA_HADOOP_HDFS_NAMENODE_RPC_PORT = "qingcheng.conf.bigdata.hadoop.hdfs.namenode.rpc.port".trim();
    private final static String QINGCHENG_CONF_BIGDATA_HADOOP_HDFS_NAMENODE_HTTP_PORT = "qingcheng.conf.bigdata.hadoop.hdfs.namenode.http.port".trim();
    private final static String QINGCHENG_CONF_BIGDATA_HADOOP_HDFS_READ_BUFFER_SIZE = "qingcheng.conf.bigdata.hadoop.hdfs.read.buffer.size".trim();
    private final static String QINGCHENG_CONF_BIGDATA_ALLUXIO_CLUSTER_REGIST_ZNODE_PATH = "qingcheng.conf.bigdata.alluxio.cluster.regist.znode.path".trim();
    private final static String QINGCHENG_CONF_BIGDATA_ALLUXIO_MASTER_HOSTNAME = "qingcheng.conf.bigdata.alluxio.master.hostname".trim();
    private final static String QINGCHENG_CONF_BIGDATA_ALLUXIO_FORCE_DISABLE = "qingcheng.conf.bigdata.alluxio.force.disable".trim();
    private final static String QINGCHENG_CONF_BIGDATA_ALLUXIO_RESTART_WAIT_TIME = "qingcheng.conf.bigdata.alluxio.restart.wait.time".trim();
    private final static String QINGCHENG_CONF_BIGDATA_ALLUXIO_READ_TYPE = "qingcheng.conf.bigdata.alluxio.read.type".trim();
    private final static String QINGCHENG_CONF_BIGDATA_ALLUXIO_READ_BUFFER_SIZE = "qingcheng.conf.bigdata.alluxio.read.buffer.size".trim();
    private final static String QINGCHENG_CONF_JAVA_APPLICATION_CLUSTER_HOSTNAME = "qingcheng.conf.java.application.cluster.hostname".trim();
    private final static String QINGCHENG_CONF_JAVA_APPLICATION_CLUSTER_REGIST_ZNODE_PATH = "qingcheng.conf.java.application.cluster.regist.znode.path".trim();
    private final static String QINGCHENG_CONF_JAVA_APPLICATION_CLUSTER_THREAD_WAIT_TIME = "qingcheng.conf.java.application.cluster.thread.wait.time".trim();
    private final static String QINGCHENG_CONF_JAVA_APPLICATION_JAR_FILE_NAME = "qingcheng.conf.java.application.jar.file.name".trim();
    private final static String QINGCHENG_CONF_JAVA_APPLICATION_JAR_FILE_DEST_PATH = "qingcheng.conf.java.application.jar.file.dest.path".trim();
    private final static String QINGCHENG_CONF_JAVA_APPLICATION_JAR_FILE_SRC_HOSTNAME = "qingcheng.conf.java.application.jar.file.src.hostname".trim();
    private final static String QINGCHENG_CONF_JAVA_APPLICATION_JAR_FILE_SRC_PATH = "qingcheng.conf.java.application.jar.file.src.path".trim();
    private final static String QINGCHENG_CONF_JAVA_APPLICATION_DETECTOR_FOR_BIGDATA_BEFORE_ONCE_DETECTOR_IS_ENABLE = "qingcheng.conf.java.application.detector.for.bigdata.before.once.detector.is.enable".trim();
    private final static String QINGCHENG_CONF_JAVA_APPLICATION_DETECTOR_FOR_BIGDATA_BEFORE_ONCE_DETECTOR_ALLUXIO_WAIT_TIME = "qingcheng.conf.java.application.detector.for.bigdata.before.once.detector.alluxio.wait.time".trim();
    private final static String QINGCHENG_CONF_JAVA_APPLICATION_DETECTOR_FOR_BIGDATA_LOOP_DETECTOR_IS_ENABLE = "qingcheng.conf.java.application.detector.for.bigdata.loop.detector.is.enable".trim();
    private final static String QINGCHENG_CONF_JAVA_APPLICATION_DETECTOR_FOR_BIGDATA_LOOP_DETECTOR_INTERVAL_TIME = "qingcheng.conf.java.application.detector.for.bigdata.loop.detector.interval.time".trim();
    private final static String QINGCHENG_CONF_JAVA_APPLICATION_DETECTOR_FOR_BIGDATA_ZNODE_DATACHANGE_RANDOM_DETECTOR_IS_ENABLE = "qingcheng.conf.java.application.detector.for.bigdata.znode.datachange.random.detector.is.enable".trim();
    private final static String QINGCHENG_CONF_JAVA_APPLICATION_DETECTOR_FOR_BIGDATA_ZNODE_DATACHANGE_RANDOM_DETECTOR_WAIT_TIME = "qingcheng.conf.java.application.detector.for.bigdata.znode.datachange.random.detector.wait.time".trim();
    private final static String QINGCHENG_CONF_JAVA_APPLICATION_DETECTOR_FOR_JAVA_LOOP_DETECTOR_IS_ENABLE = "qingcheng.conf.java.application.detector.for.java.loop.detector.is.enable".trim();
    private final static String QINGCHENG_CONF_JAVA_APPLICATION_DETECTOR_FOR_JAVA_LOOP_DETECTORR_INTERVAL_TIME = "qingcheng.conf.java.application.detector.for.java.loop.detectorr.interval.time".trim();
    private final static String QINGCHENG_CONF_JAVA_APPLICATION_MEDIA_SUPPORT_VIDEO_DATA_PRIMARY_TYPE = "qingcheng.conf.java.application.media.support.video.data.primary.type".trim();
    private final static String QINGCHENG_CONF_JAVA_APPLICATION_MEDIA_SUPPORT_IMAGE_DATA_PRIMARY_TYPE = "qingcheng.conf.java.application.media.support.image.data.primary.type".trim();
    /**
     * 1.以下是本Java程序独享变量,不需要在zookeeper上为所有机器共享
     */
    private static boolean THIS_JAVA_APPLICATION_IS_BIGDATA_AND_JAVACLUSTER_MANAGER = false;

    public static boolean isThisJavaApplicationIsBigdataAndJavaclusterManager() {
        return THIS_JAVA_APPLICATION_IS_BIGDATA_AND_JAVACLUSTER_MANAGER;
    }

    public static void setThisJavaApplicationIsBigdataAndJavaclusterManager(boolean thisJavaApplicationIsBigdataAndJavaclusterManager) {
        THIS_JAVA_APPLICATION_IS_BIGDATA_AND_JAVACLUSTER_MANAGER = thisJavaApplicationIsBigdataAndJavaclusterManager;
    }

    /**
     * 将本地配置上传到zookeeper
     */
    public static void configPropertiesInitToZookeeper() {
        //0.如果all Java application的注册的节点的父节点不存在,先上传配置文件,再添加节点监控
        String z=AppDynamic.getQingchengConfJavaApplicationClusterRegistZnodePath();
        if (!ZookeeperDao.isExistsZNode(z)) {
            //1.将本地配置上传到zookeeper
            Properties znodeNames = new Properties();
            Set<Object> localKeys = AppDynamic.LOCAL_DYNAMIC_PROPERTIES.keySet();
            for (Object key : localKeys) {
                String znodePath = AppStrUtils.convertPropertyFieldToZnodePath((String) key);
                znodeNames.put(key, AppStrUtils.convertPropertyFieldToZnodeName((String) key));
                String znodeData = (String) AppDynamic.LOCAL_DYNAMIC_PROPERTIES.get(key);
                //创建znode并初始化data
                String znode = ZookeeperDao.createPznodeWtihOutACL(znodePath, znodeData);
                AppDynamic.ZK_DYNAMIC_PROPERTIES.put(znodePath, znodeData);
            }
        }
        //2.在znode上添加监控
        Set<Object> znodePaths = AppDynamic.ZK_DYNAMIC_PROPERTIES.keySet();
        for (Object znodePath : znodePaths) {
            ZookeeperDao.watchForZnodeChange((String) znodePath, appDynamicWatcher);
        }
    }

    /**
     * 通過監聽,使本地配置與zookeeper完全一致!
     */
    public static class AppDynamicWatcher extends ZooKeeperWatcherBizDoerAdapter {
        @Override
        public void znodeChange(NodeCache nodeCache) {
            String data = new String(nodeCache.getCurrentData().getData());
            String path = nodeCache.getCurrentData().getPath();
            ZK_DYNAMIC_PROPERTIES.put(path, data);
            System.out.println(path + "####" + data);
            System.out.println(ZK_DYNAMIC_PROPERTIES);
        }
    }

    public static String getQingchengConfJavaApplicationDetectorForBigdataZnodeDatachangeRandomDetectorIsEnable() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_JAVA_APPLICATION_DETECTOR_FOR_BIGDATA_ZNODE_DATACHANGE_RANDOM_DETECTOR_IS_ENABLE));
    }

    public static int getQingchengConfJavaApplicationDetectorForBigdataBeforeOnceDetectorAlluxioWaitTime() {
        return Integer.parseInt((String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_JAVA_APPLICATION_DETECTOR_FOR_BIGDATA_BEFORE_ONCE_DETECTOR_ALLUXIO_WAIT_TIME)));
    }

    public static String getQingchengConfJavaApplicationDetectorForBigdataLoopDetectorIsEnable() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_JAVA_APPLICATION_DETECTOR_FOR_BIGDATA_LOOP_DETECTOR_IS_ENABLE));
    }

    public static int getQingchengConfBigdataAlluxioReadBufferSize() {
        return Integer.parseInt((String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_BIGDATA_ALLUXIO_READ_BUFFER_SIZE)));
    }

    public static String getQingchengConfBigdataHadoopHdfsNamenodeHostname() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_BIGDATA_HADOOP_HDFS_NAMENODE_HOSTNAME));
    }

    public static List<String> getQingchengConfBigdataHadoopHdfsNamenodeHostnameList() {
        return AppStrUtils.getHostListFromStringWithComma(getQingchengConfBigdataHadoopHdfsNamenodeHostname());
    }

    public static String getQingchengConfBigdataHadoopHdfsNamenodePresetHostname() {
        //默認的namenode
        return getQingchengConfBigdataHadoopHdfsNamenodeHostnameList().get(0);
    }

    public static String getQingchengConfJavaApplicationMediaSupportVideoDataPrimaryType() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_JAVA_APPLICATION_MEDIA_SUPPORT_VIDEO_DATA_PRIMARY_TYPE));
    }

    public static String getQingchengConfBigdataAlluxioMasterHostname() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_BIGDATA_ALLUXIO_MASTER_HOSTNAME));
    }

    public static String getQingchengConfBigdataHadoopHdfsNamenodeRpcPort() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_BIGDATA_HADOOP_HDFS_NAMENODE_RPC_PORT));
    }

    public static String getQingchengConfBigdataHadoopClusterRegistZnodePath() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_BIGDATA_HADOOP_CLUSTER_REGIST_ZNODE_PATH));
    }

    public static String getQingchengConfBigdataHadoopClusterRegistZnodeFullPathParent() {
        //監控路徑
        return ("/hadoop-ha/" + getQingchengConfBigdataHadoopClusterRegistZnodePath()).trim();

    }

    public static String getQingchengConfBigdataHadoopClusterRegistZnodeFullPathSon() {
        //監控路徑
        return (AppDynamic.getQingchengConfBigdataHadoopClusterRegistZnodeFullPathParent() + "/ActiveStandbyElectorLock").trim();

    }

    public static String getQingchengConfBigdataHadoopHdfsNamenodeHttpPort() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_BIGDATA_HADOOP_HDFS_NAMENODE_HTTP_PORT));
    }

    public static int getQingchengConfBigdataHadoopHdfsReadBufferSize() {
        return Integer.parseInt((String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_BIGDATA_HADOOP_HDFS_READ_BUFFER_SIZE)));
    }

    public static int getQingchengConfJavaApplicationDetectorForBigdataZnodeDatachangeRandomDetectorWaitTime() {
        return Integer.parseInt((String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_JAVA_APPLICATION_DETECTOR_FOR_BIGDATA_ZNODE_DATACHANGE_RANDOM_DETECTOR_WAIT_TIME)));
    }

    public static String getQingchengConfJavaApplicationClusterHostname() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_JAVA_APPLICATION_CLUSTER_HOSTNAME));
    }

    public static List<String> getQingchengConfJavaApplicationClusterHostnameList() {
        return AppStrUtils.getHostListFromStringWithComma(getQingchengConfJavaApplicationClusterHostname());
    }

    public static boolean getQingchengConfBigdataAlluxioForceDisable() {
        return Boolean.parseBoolean((String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_BIGDATA_ALLUXIO_FORCE_DISABLE)));
    }

    public static String getQingchengConfJavaApplicationJarFileSrcHostname() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_JAVA_APPLICATION_JAR_FILE_SRC_HOSTNAME));
    }

    public static String getQingchengConfJavaApplicationJarFileSrcPath() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_JAVA_APPLICATION_JAR_FILE_SRC_PATH));
    }

    public static String getQingchengConfJavaApplicationMediaSupportImageDataPrimaryType() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_JAVA_APPLICATION_MEDIA_SUPPORT_IMAGE_DATA_PRIMARY_TYPE));
    }

    public static String getQingchengConfJavaApplicationJarFileDestPath() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_JAVA_APPLICATION_JAR_FILE_DEST_PATH));
    }
    public static String getQingchengConfJavaApplicationClusterRegistZnodePathPre() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(QINGCHENG_CONF_JAVA_APPLICATION_CLUSTER_REGIST_ZNODE_PATH);
    }
    public static String getQingchengConfJavaApplicationClusterRegistZnodePath() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_JAVA_APPLICATION_CLUSTER_REGIST_ZNODE_PATH));
    }

    public static String getQingchengConfJavaApplicationDetectorForBigdataBeforeOnceDetectorIsEnable() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_JAVA_APPLICATION_DETECTOR_FOR_BIGDATA_BEFORE_ONCE_DETECTOR_IS_ENABLE));
    }

    public static String getQingchengConfBigdataHadoopHdfsJournalnodeHostname() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_BIGDATA_HADOOP_HDFS_JOURNALNODE_HOSTNAME));
    }

    public static List<String> getQingchengConfBigdataHadoopHdfsJournalnodeHostnameList() {
        return AppStrUtils.getHostListFromStringWithComma(getQingchengConfBigdataHadoopHdfsJournalnodeHostname());
    }

    public static String getQingchengConfJavaApplicationDetectorForJavaLoopDetectorIsEnable() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_JAVA_APPLICATION_DETECTOR_FOR_JAVA_LOOP_DETECTOR_IS_ENABLE));
    }

    public static int getQingchengConfBigdataAlluxioRestartWaitTime() {
        return Integer.parseInt((String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_BIGDATA_ALLUXIO_RESTART_WAIT_TIME)));
    }

    public static String getQingchengConfBigdataHadoopHdfsDatanodeHostname() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_BIGDATA_HADOOP_HDFS_DATANODE_HOSTNAME));
    }

    public static List<String> getQingchengConfBigdataHadoopHdfsDatanodeHostnameList() {
        return AppStrUtils.getHostListFromStringWithComma(getQingchengConfBigdataHadoopHdfsDatanodeHostname());
    }

    public static int getQingchengConfJavaApplicationDetectorForBigdataLoopDetectorIntervalTime() {
        return Integer.parseInt((String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_JAVA_APPLICATION_DETECTOR_FOR_BIGDATA_LOOP_DETECTOR_INTERVAL_TIME)));
    }

    public static String getQingchengConfBigdataAlluxioClusterRegistZnodePath() {
        return AppStrUtils.convertStringToZnodePath((String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_BIGDATA_ALLUXIO_CLUSTER_REGIST_ZNODE_PATH)));
    }

    public static int getQingchengConfJavaApplicationDetectorForJavaLoopDetectorrIntervalTime() {
        return Integer.parseInt((String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_JAVA_APPLICATION_DETECTOR_FOR_JAVA_LOOP_DETECTORR_INTERVAL_TIME)));
    }

    public static int getQingchengConfJavaApplicationClusterThreadWaitTime() {
        return Integer.parseInt((String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_JAVA_APPLICATION_CLUSTER_THREAD_WAIT_TIME)));
    }

    public static String getQingchengConfJavaApplicationJarFileName() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_JAVA_APPLICATION_JAR_FILE_NAME));
    }

    public static String getQingchengConfBigdataAlluxioReadType() {
        return (String) ZK_DYNAMIC_PROPERTIES.get(AppStrUtils.convertPropertyFieldToZnodePath(QINGCHENG_CONF_BIGDATA_ALLUXIO_READ_TYPE));
    }

    public static List<String> getAllHostInServerCluster() {
        List<String> allHosts = new ArrayList<>();
        allHosts.addAll(AppStatic.getZookeeperClusterHostsList());
        allHosts.addAll(AppDynamic.getQingchengConfBigdataHadoopHdfsNamenodeHostnameList());
        allHosts.addAll(AppDynamic.getQingchengConfBigdataHadoopHdfsDatanodeHostnameList());
        allHosts.addAll(AppDynamic.getQingchengConfBigdataHadoopHdfsJournalnodeHostnameList());
        allHosts.addAll(AppDynamic.getQingchengConfJavaApplicationClusterHostnameList());
        allHosts.add(AppDynamic.getQingchengConfBigdataAlluxioMasterHostname());
        List<String> JAVA_AND_BIGDATA_CLUSTER_HOST_LIST = new ArrayList<>();
        for (String host : allHosts) {
            if (!JAVA_AND_BIGDATA_CLUSTER_HOST_LIST.contains(host)) {
                JAVA_AND_BIGDATA_CLUSTER_HOST_LIST.add(host);

            }
        }
        return JAVA_AND_BIGDATA_CLUSTER_HOST_LIST;
    }


}
