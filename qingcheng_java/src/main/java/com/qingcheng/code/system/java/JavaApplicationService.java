/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.system.java;

import com.qingcheng.code.common.constant.AppConstants;
import com.qingcheng.code.common.exception.FileIsNotExistInClusterException;
import com.qingcheng.code.common.properties.AppDynamic;
import com.qingcheng.code.common.properties.AppStatic;
import com.qingcheng.code.common.util.basic.collect.AppCollectionUtil;
import com.qingcheng.code.common.util.advance.shell.LocalShellExecutorBasicService;
import com.qingcheng.code.common.util.advance.shell.RemoteShellExecutor;
import com.qingcheng.code.common.util.basic.str.AppStrUtils;
import com.qingcheng.code.system.bigdata.zookeeper.dao.ZookeeperDao;
import com.qingcheng.code.system.host.HostClusterService;
import com.qingcheng.code.system.service.AllServiceManager;

import java.util.*;

/**
 * Created by liguohua on 16/8/27.
 */
public class JavaApplicationService {
    private final static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(JavaApplicationService.class);

    /**
     * 复制jar文件到,应该有而没有的机器列表中
     * 1.预定于机器上有jar,从预定义机器上复制
     * 2.预定义机器上无jar,从其他有jar的机器上复制
     * 3.所有机器上都唔jar文件,则报错
     */
    public static void copyJavaJarFileToShouldHaveJarFileHosts() {
        //此Java程序不是java application的监控者
        if (!AppDynamic.isThisJavaApplicationIsBigdataAndJavaclusterManager()) {
            logger.info("初始化时期的一次判定:经判定此java程序,目前还不是jar的守护者,THIS_JAVA_APPLICATION_IS_BIGDATA_AND_JAVACLUSTER_MANAGER=" + AppDynamic.isThisJavaApplicationIsBigdataAndJavaclusterManager());
            return;
        }
        //1.获取应当复制文件的机器列表
        List<String> shouldScpJarFileHosts = getShouldScpJavaJarFileHostList();
        String copyFromHostName = "";
        if (!shouldScpJarFileHosts.contains(AppDynamic.getQingchengConfJavaApplicationJarFileSrcHostname())) {
            //2.如果预定于机器上没有jar文件,尽力最大能力,从有jar文件的机器上复制
            List<String> hasJarFileHostList = new LinkedList<>();
            hasJarFileHostList.addAll(AppDynamic.getQingchengConfJavaApplicationClusterHostnameList());
            hasJarFileHostList.removeAll(shouldScpJarFileHosts);
            if (!AppCollectionUtil.isEmpty(hasJarFileHostList)) {
                //有的机器上有jar文件,从第一个机器复制
                copyFromHostName = hasJarFileHostList.get(0);
            } else {
                //所有机器上都没有jar文件
                throw new FileIsNotExistInClusterException(FileIsNotExistInClusterException.MSG + AppDynamic.getQingchengConfJavaApplicationJarFileDestPath() + AppDynamic.getQingchengConfJavaApplicationJarFileName());
            }
        } else {
            //3.如果预定于机器上有jar文件,优先将预定于机器作为复制源
            copyFromHostName = AppDynamic.getQingchengConfJavaApplicationJarFileSrcHostname();
        }
        //杀死复制了jar文件的机器上的Java程序
        // stopCanbeStopJavaApplicationHostList(shouldScpJarFileHosts);
        //复制jar文件
        HostClusterService.clusterSCP_NoChangeDest(copyFromHostName, AppDynamic.getQingchengConfJavaApplicationJarFileSrcPath() + AppDynamic.getQingchengConfJavaApplicationJarFileName(), shouldScpJarFileHosts);

    }

    /**
     * 获取需要复制jar包的主机列表
     *
     * @return 需要复制jar包的主机列表
     */
    private static List<String> getShouldScpJavaJarFileHostList() {
        Map<String, List<String>> map = HostClusterService.clusterLS(AppDynamic.getQingchengConfJavaApplicationClusterHostnameList(), AppDynamic.getQingchengConfJavaApplicationJarFileDestPath());
        List<String> shouldScpJarFileHosts = new LinkedList<>();
        final Set<String> hostNames = map.keySet();
        for (Object hostName : hostNames) {
            List<String> oneHostResult = map.get(hostName);
            if (!oneHostResult.contains(AppDynamic.getQingchengConfJavaApplicationJarFileName())) {
                shouldScpJarFileHosts.add((String) hostName);
            }
        }
        return shouldScpJarFileHosts;
    }

    /**
     * Java程序初始化,
     */
    public static void javaApplicationInit() {
        //1.注册本机到zookeeper集群
        JavaApplicationService.javaApplicationRegistHostnameOnZookeeper();
        //2.监听目录,判断自己是不是监视者
        ZookeeperDao.watchForChildZnodesChange(AppDynamic.getQingchengConfJavaApplicationClusterRegistZnodePath(), new JavaApplicationBigdataWatcher());
        //3.将jar文件复制到目标主机上
        copyJavaJarFileToShouldHaveJarFileHosts();
    }

    /**
     * Java程序启动后自动在zookeeper上注册自己
     *
     * @return 注册的znode节点
     */
    public static String javaApplicationRegistHostnameOnZookeeper() {
        //用本地机器名注册带序列号的临时节点
        String znodePath = AppDynamic.getQingchengConfJavaApplicationClusterRegistZnodePath() + LocalShellExecutorBasicService.getLocalHostName() + ZookeeperDao.ZNODE_SEQUENTIAL_SPERERATER;
        String znodeContent = LocalShellExecutorBasicService.getLocalHostName();
        return ZookeeperDao.createESznodeWtihOutACL(znodePath, znodeContent);
    }

    /**
     * 查询可以启动的Java application hostname list
     *
     * @return 可以启动Java程序的机器列表
     */
    public static List<String> getShouldStartJavaApplicationHostList() {
        Map<String, List<String>> result = HostClusterService.clusterJPS(AppDynamic.getQingchengConfJavaApplicationClusterHostnameList(), AppStatic.getServerUserName(), AppStatic.getServerPassWord());
        return AllServiceManager.getShouldStartServiceHostList(result, AppDynamic.getQingchengConfJavaApplicationClusterHostnameList(), AppDynamic.getQingchengConfJavaApplicationJarFileName());
    }


    /**
     * 查询可以停止的Java application hostname list
     *
     * @return 可以停止Java程序的机器列表
     */
    public static List<String> getCanbeStopJavaApplicationHostList() {
        Map<String, List<String>> result = HostClusterService.clusterJPS(AppDynamic.getQingchengConfJavaApplicationClusterHostnameList(), AppStatic.getServerUserName(), AppStatic.getServerPassWord());
        return AllServiceManager.canbeStopServiceHostList(result, AppDynamic.getQingchengConfJavaApplicationClusterHostnameList(), AppDynamic.getQingchengConfJavaApplicationJarFileName());
    }

    /**
     * 启动Java集群
     */
    public static void startShouldStartJavaApplicationHostList() {
        //应该被启动Java程序的机器列表
        List<String> hosts = getShouldStartJavaApplicationHostList();
        startJavaApplicationByHostList(hosts);
    }

    public static void startJavaApplicationByHostList(Collection<String> hosts) {
        //1.生成启动命令
        List<String> cmds = new LinkedList<>();
        String javaJar = AppDynamic.getQingchengConfJavaApplicationJarFileDestPath() + AppDynamic.getQingchengConfJavaApplicationJarFileName();
        String tempsh = AppDynamic.getQingchengConfJavaApplicationJarFileDestPath() + "temp.sh";
        cmds.add("rm -rf " + tempsh);
        cmds.add("echo 'nohup java -jar " + javaJar + " ' >> " + tempsh);
        cmds.add("chmod ugo+rwx " + tempsh);
        cmds.add(tempsh);
        cmds.add("rm -rf " + tempsh);
        //2.生成可以启动Java程序的机器列表
        logger.info("should start java application nodes= " + hosts.toString());
        //3.为每一个机器开启一个新的线程,启动Java程序
        for (String host : hosts) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    RemoteShellExecutor.executeShellCommandsOnHostWhitOutResponse(host, cmds);
                }
            });
            thread.setDaemon(true);
            thread.start();
        }
        //4.反馈启动情况
        HostClusterService.clusterJPS(AppDynamic.getQingchengConfJavaApplicationClusterHostnameList(), AppStatic.getServerUserName(), AppStatic.getServerPassWord());
    }


    /**
     * 停止Java集群
     */
    public static void stopCanbeStopJavaApplicationHostList(Collection<String> hostNames) {
        //1.执行jps,获取机器中运行进程的情况
        Map<String, List<String>> result = HostClusterService.clusterJPS(hostNames, AppStatic.getServerUserName(), AppStatic.getServerPassWord());
        //2.根据进程情况,找出可以停止Java程序的机器列表
        List<String> hostList = AllServiceManager.canbeStopServiceHostList(result, AppDynamic.getQingchengConfJavaApplicationClusterHostnameList(), AppDynamic.getQingchengConfJavaApplicationJarFileName());
        for (String host : hostList) {
            //2.1过滤每个机器上的输出信息,得到可以执行的命令
            List<String> oneHostOutPut = result.get(host);
            List<String> cmds = new ArrayList<>();
            for (String oneRecordOutput : oneHostOutPut) {
                if (oneRecordOutput.contains(AppDynamic.getQingchengConfJavaApplicationJarFileName())) {
                    //2.2将输出信息的每一个行,已经空格解析成进程ID和进程名称
                    String[] process_id_name = oneRecordOutput.split(AppConstants.BLANK);
                    //2.3根据进程ID生成杀死进程的命令
                    if (process_id_name.length == 2) {
                        cmds.add("kill -9 " + process_id_name[0].trim());
                    }
                }
            }
            //3.分别在每个机器上执行这些命令
            RemoteShellExecutor.executeShellCommandsOnHostWhitOutResponse(host, cmds);
            //4.再次执行jps,查看进程信息
            HostClusterService.clusterJPS(AppDynamic.getQingchengConfJavaApplicationClusterHostnameList(), AppStatic.getServerUserName(), AppStatic.getServerPassWord());

        }
    }

    /**
     * 在集群中强制重新部署Java程序
     */
    public static void forecFullReDeployJavaApplication() {
        List<String> javaHostList = AppDynamic.getQingchengConfJavaApplicationClusterHostnameList();
        String jarFileName = AppDynamic.getQingchengConfJavaApplicationJarFileName();
        String jarDestPath = AppDynamic.getQingchengConfJavaApplicationJarFileDestPath();
        String jarSrcHost = AppDynamic.getQingchengConfJavaApplicationJarFileSrcHostname();
        String jarSrcFilePath = AppDynamic.getQingchengConfJavaApplicationJarFileSrcPath();
        //1.强制停止Java程序
        JavaApplicationService.forceStopJavaApplicationDeamon(javaHostList);
        //2.强制删除jar文件
        JavaApplicationService.forceRemoveJarFile(javaHostList, jarFileName, jarDestPath);
        //3.强制部署jar文件
        JavaApplicationService.froceDeployJarFile(javaHostList, jarFileName, jarDestPath, jarSrcHost, jarSrcFilePath);
        //4.强制启动Java程序
        JavaApplicationService.forceStartJavaApplication(javaHostList);
    }

    /**
     * 强制在集群中杀死Java程序
     *
     * @param javaHostList Java程序集群
     */
    public static void forceStartJavaApplication(List<String> javaHostList) {
        JavaApplicationService.startJavaApplicationByHostList(javaHostList);
    }

    /**
     * 强制在机器中杀死Java程序
     *
     * @param javaHostList Java程序集群
     */
    public static void forceStopJavaApplicationDeamon(List<String> javaHostList) {
        JavaApplicationService.stopCanbeStopJavaApplicationHostList(javaHostList);
    }

    /**
     * 强制在机器中部署jar文件
     *
     * @param javaHostList   Java程序集群
     * @param jarFileName    jarFileName
     * @param jarDestPath    jarDestPath
     * @param jarSrcHost     jarSrcHost
     * @param jarSrcFilePath jarSrcFilePath
     */
    public static void froceDeployJarFile(List<String> javaHostList, String jarFileName, String jarDestPath, String jarSrcHost, String jarSrcFilePath) {
        //1.复制jar文件
        HostClusterService.clusterSCP_ChangeDest(jarSrcHost, AppStrUtils.getFileAbsolutePathByPathAndName(jarSrcFilePath, jarFileName), javaHostList, jarDestPath);
        //2.复制后f反馈
        HostClusterService.clusterLS(javaHostList, jarDestPath);
    }

    /**
     * 强制在机器中删除jar文件
     *
     * @param javaHostList Java程序集群
     * @param jarFileName  jarFileName
     * @param jarDestPath  jarDestPath
     */
    public static void forceRemoveJarFile(List<String> javaHostList, String jarFileName, String jarDestPath) {
        HostClusterService.clusterRMRF(javaHostList, AppStrUtils.getFileAbsolutePathByPathAndName(jarDestPath, jarFileName));
    }


}
