/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.common.util.advance.shell;

/**
 * Created by liguohua on 16/9/10.
 * 此类用于 LocalShellExecutor 的基本服务
 */
public class LocalShellExecutorBasicService {
    private static final String EXECUTE_CMD_HOSTNAMEEXECUTE_CMD_HOSTNAME = "hostname";

    /**
     * 获取本机机器名称
     *
     * @return 本机机器名称
     */
    public static String getLocalHostName() {
        return LocalShellExecutor.exexcutorShellCommondsWithResponse(EXECUTE_CMD_HOSTNAMEEXECUTE_CMD_HOSTNAME);
    }
}
