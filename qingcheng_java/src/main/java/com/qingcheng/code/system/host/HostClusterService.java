/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.system.host;

import com.qingcheng.code.common.constant.AppConstants;
import com.qingcheng.code.common.properties.AppDynamic;
import com.qingcheng.code.common.properties.AppStatic;
import com.qingcheng.code.common.util.advance.shell.RemoteShellExecutor;
import com.qingcheng.code.common.util.basic.str.AppStrUtils;

import java.util.*;

/**
 * Created by liguohua on 16/8/21.
 */
public class HostClusterService {
    /**
     * 集群中执行删除命名
     *
     * @param targetClusterHostList 执行命令的机器列表
     * @param file                  要删除的文件
     */
    public static void clusterRMRF(Collection<String> targetClusterHostList, String file) {
        List<String> files = new LinkedList<>();
        files.add(file);
        clusterRMRF(targetClusterHostList, files);
    }

    /**
     * 集群中执行删除命名
     *
     * @param targetClusterHostList 执行命令的机器列表
     * @param files                 要删除的文件列表
     */
    public static void clusterRMRF(Collection<String> targetClusterHostList, Collection<String> files) {
        for (String h : targetClusterHostList) {
            List<String> cmds = new ArrayList<>();
            for (String f : files) {
                String cmd = "rm  -rf " + f;
                cmds.add(cmd);
            }
            RemoteShellExecutor.executeShellCommandsOnHostWhitOutResponse(h, cmds);
        }
    }

    /**
     * 将一个文件发送到集群中.
     *
     * @param srcHost               文件所在的机器
     * @param file                  文件
     * @param targetClusterHostList 发送文件到目标集群
     */
    public static void clusterSCP_ChangeDest(String srcHost, String file, Collection<String> targetClusterHostList, String destPath) {
        List<String> files = new LinkedList<>();
        files.add(file);
        clusterSCP_ChangeDest(srcHost, files, targetClusterHostList, destPath);
    }

    /**
     * 将多个文件发送到集群中.
     *
     * @param srcHost  文件所在的机器
     * @param files    文件列表
     * @param destPath 发送文件的目标路径
     */
    public static void clusterSCP_ChangeDest(String srcHost, Collection<String> files, Collection<String> targetClusterHostList, String destPath) {
        clusterSCP(srcHost, files, targetClusterHostList, destPath);
    }

    /**
     * 将一个文件发送到集群中.
     *
     * @param srcHost               文件所在的机器
     * @param file                  文件
     * @param targetClusterHostList 发送文件到目标集群
     */
    public static void clusterSCP_NoChangeDest(String srcHost, String file, Collection<String> targetClusterHostList) {
        List<String> files = new LinkedList<>();
        files.add(file);
        clusterSCP_NoChangeDest(srcHost, files, targetClusterHostList);
    }

    /**
     * 将多个文件发送到集群中.
     *
     * @param srcHost               文件所在的机器
     * @param files                 文件列表
     * @param targetClusterHostList 发送文件到目标集群
     */
    public static void clusterSCP_NoChangeDest(String srcHost, Collection<String> files, Collection<String> targetClusterHostList) {
        clusterSCP(srcHost, files, targetClusterHostList, "");
    }

    /**
     * 将多个文件发送到集群中.
     *
     * @param srcHost               文件所在的机器
     * @param files                 文件列表
     * @param targetClusterHostList 发送文件到目标集群
     * @param destPath              发送文件的目标路径
     */
    public static void clusterSCP(String srcHost, Collection<String> files, Collection<String> targetClusterHostList, String destPath) {
        List<String> cmds = new ArrayList<>();
        for (String h : targetClusterHostList) {
            for (String f : files) {
                String destAbsolutePath = f;
                if (!AppStrUtils.isEmptyStr(destPath)) {
                    //如果变路径，则不再使用src的绝对路径
                    destAbsolutePath = AppStrUtils.getFileAbsolutePathByPathAndName(AppStrUtils.filePathDoer(destPath), AppStrUtils.getFileNameByFileAbsolutePath(f));
                }
                String cmd = "scp  -r ";
                cmd = cmd + f + AppConstants.BLANK + h + AppConstants.COLON + destAbsolutePath;
                cmds.add(cmd);
            }
        }
        RemoteShellExecutor.executeShellCommandsOnHostWhitOutResponse(srcHost, cmds);
    }

    /**
     * 集群中执行 jps操作
     *
     * @param allHosts 执行操作的目标集群
     * @param path     命令执行的目录
     * @return 操作后的返回值
     */
    public static Map<String, List<String>> clusterLS(Collection<String> allHosts, String path) {
        String option = AppConstants.BLANK;
        return clusterLS(allHosts, option, path);
    }

    /**
     * 集群中执行 jps操作
     *
     * @param allHosts 执行操作的目标集群
     * @param options  命令的选项
     * @param path     命令执行的目录
     * @return 操作后的返回值
     */
    private static Map<String, List<String>> clusterLS(Collection<String> allHosts, String options, String path) {
        String cmd = "ls " + options + AppStrUtils.filePathDoer(path);
        //处理前的结果
        Map<String, List<String>> r1 = RemoteShellExecutor.executeShellCommandOnClusterWhiteResponse(allHosts, AppStatic.getServerUserName(), AppStatic.getServerPassWord(), cmd);
        //处理后的结果
        Map<String, List<String>> r2 = new HashMap<>();
        final Set<String> keys1 = r1.keySet();
        for (Object k1 : keys1) {
            List<String> l1 = r1.get(k1);
            for (String s1 : l1) {
                String[] split = s1.split(AppConstants.BLANK);
                if (split != null && split.length > 0) {
                    List<String> l2 = new LinkedList<>();
                    for (String s : split) {
                        if (AppStrUtils.isEmptyStr(s)) {
                            continue;
                        }
                        l2.add(s);
                    }
                    r2.put((String) k1, l2);
                }
            }
        }
        return r2;
    }


    /**
     * 集群中执行 jps操作
     *
     * @param allHosts 执行操作的目标集群
     * @return 操作后的返回值
     */
    public static Map<String, List<String>> clusterJPS(Collection<String> allHosts) {
        return clusterJPS(allHosts, AppStatic.getServerUserName(), AppStatic.getServerPassWord());
    }

    /**
     * 集群中执行 jps操作
     *
     * @param allHosts 执行操作的目标集群
     * @param username login username
     * @param password login password
     * @return 操作后的返回值
     */
    public static Map<String, List<String>> clusterJPS(Collection<String> allHosts, String username, String password) {
        return RemoteShellExecutor.executeShellCommandOnClusterWhiteResponse(allHosts, username, password, "jps");
    }

    /**
     * 集群中执行 jps操作
     *
     * @param allHosts 执行操作的目标集群
     * @return 操作后的返回值
     */
    public static Map<String, List<String>> clusterJpsMutiThread(Collection<String> allHosts) {
        return RemoteShellExecutor.executeShellCommandOnClusterWhiteResponseMutilThread(allHosts, AppStatic.getServerUserName(), AppStatic.getServerPassWord(), "jps");
    }

    /**
     * 集群中执行 reboot操作
     *
     * @param allHosts 执行操作的目标集群
     * @return 操作后的返回值
     */
    public static void clusterReboot(Collection<String> allHosts) {
        for (String host : allHosts) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    RemoteShellExecutor.executeShellCommandOnHostWhitOutResponse(host, "reboot");
                }
            });
            thread.setDaemon(false);
            thread.start();
        }
        try {
            Thread.sleep(AppDynamic.getQingchengConfJavaApplicationClusterThreadWaitTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
