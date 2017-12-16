/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.common.util.advance.shell;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import com.qingcheng.code.common.constant.AppConstants;
import com.qingcheng.code.common.properties.AppDynamic;
import com.qingcheng.code.common.properties.AppStatic;
import com.qingcheng.code.common.util.basic.str.AppStrUtils;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;


/**
 * Created by liguohua on 16/8/21.
 */
public class RemoteShellExecutor {
    private final static Logger logger = Logger.getLogger(RemoteShellExecutor.class);

    /**
     * 多个集群执行shell命令的方法
     *
     * @param hostnames target cluster hostname
     * @param cmd       command
     */
    public static void executeShellCommandOnClusterWhiteOutResponse(String[] hostnames, String cmd) {
        executeShellCommandOnClusterWhiteOutResponse(Arrays.asList(hostnames), cmd);
    }


    /**
     * 多个集群执行shell命令的方法
     *
     * @param hostnames target cluster hostname
     * @param cmd       command
     */
    public static void executeShellCommandOnClusterWhiteOutResponse(Collection<String> hostnames, String cmd) {
        for (String hostname : hostnames) {
            executeShellCommandOnHostWhitOutResponse(hostname, AppStatic.getServerUserName(), AppStatic.getServerPassWord(), cmd);
        }
    }

    /**
     * 多个集群执行shell命令的方法,有返回值
     *
     * @param hostnames target cluster  hostname
     * @param username  login  username
     * @param password  login password
     * @param cmd       command
     */
    public static Map<String, List<String>> executeShellCommandOnClusterWhiteResponse(String[] hostnames, String username, String password, String cmd) {
        return executeShellCommandOnClusterWhiteResponse(Arrays.asList(hostnames), username, password, cmd);
    }

    /**
     * 多个集群执行shell命令的方法,无返回值
     *
     * @param hostnames target cluster hostname
     * @param username  login  username
     * @param password  login password
     * @param cmd       command
     */
    public static Map<String, List<String>> executeShellCommandOnClusterWhiteResponse(Collection<String> hostnames, String username, String password, String cmd) {
        Map<String, List<String>> allHostsResult = new HashMap<>();
        for (String hostname : hostnames) {
            List<String> oneHostResult = executeShellCommandOnHostWhitResponse(hostname, username, password, cmd);
            allHostsResult.put(hostname, oneHostResult);
        }
        return allHostsResult;
    }

    /**
     * 多个集群执行shell命令的方法,无返回值,多线程执行
     *
     * @param hostnames target cluster hostname
     * @param username  login  username
     * @param password  login password
     * @param cmd       command
     */
    public static Map<String, List<String>> executeShellCommandOnClusterWhiteResponseMutilThread(Collection<String> hostnames, String username, String password, String cmd) {
        Map<String, List<String>> allHostsResult = new HashMap<>();
        for (String hostname : hostnames) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    List<String> oneHostResult = executeShellCommandOnHostWhitResponse(hostname, username, password, cmd);
                    allHostsResult.put(hostname, oneHostResult);
                }
            }).start();

        }
        //线程等待时间
        try {
            Thread.sleep(AppDynamic.getQingchengConfJavaApplicationClusterThreadWaitTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allHostsResult;
    }

    /**
     * 单个机器执行shell命令的方法,无返回值
     *
     * @param hostname target hostname
     * @param cmd      command
     */
    public static void executeShellCommandOnHostWhitOutResponse(String hostname, String cmd) {
        executeShellCommandOnHostWhitOutResponse(hostname, AppStatic.getServerUserName(), AppStatic.getServerPassWord(), cmd);
    }

    /**
     * 单个机器执行shell命令的方法,有返回值
     *
     * @param hostname target hostname
     * @param cmd      command
     */
    public static List<String> executeShellCommandOnHostWhitResponse(String hostname, String cmd) {
        return executeShellCommandOnHostWhitResponse(hostname, AppStatic.getServerUserName(), AppStatic.getServerPassWord(), cmd);
    }

    /**
     * 单个机器执行shell命令的方法,有返回值
     *
     * @param hostname target hostname
     * @param cmds     commands
     */
    public static void executeShellCommandsOnHostWhitOutResponse(String hostname, Collection<String> cmds) {
        executeShellCommandsOnHost(hostname, AppStatic.getServerUserName(), AppStatic.getServerPassWord(), cmds, false);
    }

    /**
     * 不需要返回值的,单个机器执行shell命令的方法
     *
     * @param hostname target hostname
     * @param username login  username
     * @param password login password
     * @param cmd      command
     */
    public static void executeShellCommandOnHostWhitOutResponse(String hostname, String username, String password, String cmd) {
        executeShellCommandOnHost(hostname, username, password, cmd, false);
    }

    /**
     * 需要返回值的,单个机器执行shell命令的方法
     *
     * @param hostname target hostname
     * @param username login  username
     * @param password login password
     * @param cmd      command
     * @return response messape
     */
    public static List<String> executeShellCommandOnHostWhitResponse(String hostname, String username, String password, String cmd) {
        return executeShellCommandOnHost(hostname, username, password, cmd, true);
    }

    /**
     * 需要或不需要返回值的通用执行体,单命令执行
     *
     * @param hostname     target hostname
     * @param username     login  username
     * @param password     login password
     * @param cmd          command
     * @param needResponse need response
     * @return response messape
     */
    public static List<String> executeShellCommandOnHost(String hostname, String username, String password, String cmd, boolean needResponse) {
        List<String> cmds = new ArrayList<>();
        cmds.add(cmd);
        return executeShellCommandsOnHost(hostname, username, password, cmds, needResponse);
    }

    /**
     * 需要或不需要返回值的通用执行体,多命令执行
     *
     * @param hostname     target hostname
     * @param username     login  username
     * @param password     login password
     * @param cmds         commands
     * @param needResponse need response
     * @return response messape
     */
    public static List<String> executeShellCommandsOnHost(String hostname, String username, String password, Collection<String> cmds, boolean needResponse) {
        //1.参数检查
        if (AppStrUtils.isEmptyStr(hostname)) {
            return null;
        }
        if (cmds == null || cmds.size() == 0) {
            return null;
        }
        List<String> resultOutput = new ArrayList<>();
        //指明连接主机的IP地址
        Connection conn = new Connection(hostname);
        Session sshSession = null;
        String failCmd = null;
        try {
            //连接到主机
            logger.info("正在尝试连接" + hostname + "主机......");
            conn.connect();
            //使用用户名和密码校验
            logger.info("正在尝试验证" + hostname + "主机......");
            boolean isconn = conn.authenticateWithPassword(username, password);
            if (!isconn) {
                logger.info("用户名或密码有误,主机" + hostname + "验证失败!请确保用户名和密码正确无误!");
            } else {
                for (String cmd : cmds) {
                    sshSession = conn.openSession();
                    sshSession.requestPTY("bash");
                    logger.info("正在尝试执行" + hostname + "主机......");
                    logger.info("正在尝试执行" + cmd + "命令......");
                    failCmd = cmd;
                    sshSession.execCommand(cmd);
                    //将Terminal屏幕上的文字全部打印出来
                    InputStream is = new StreamGobbler(sshSession.getStdout());
                    BufferedReader brs = new BufferedReader(new InputStreamReader(is));
                    while (true) {
                        String line = brs.readLine();
                        if (line == null) {
                            break;
                        }
                        if (needResponse) {
                            resultOutput.add(line);
                        }
                    }
                    logger.info("主机=" + hostname + ",命令=" + cmd + "执行成功!");
                    for (String out : resultOutput) {
                        logger.info(out);
                    }
                }
            }
            logger.info(AppConstants.BLANK);
        } catch (IOException e) {
            logger.info("主机=" + hostname + ",命令=" + failCmd + "执行失败!");
            e.printStackTrace();
        } finally {
            //连接的Session和Connection对象都需要关闭
            sshSession.close();
            conn.close();
        }
        return resultOutput;
    }


}
