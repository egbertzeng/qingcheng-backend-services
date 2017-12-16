/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.common.exception;

/**
 * Created by liguohua on 16/9/11.
 */
public class FileIsNotExistInClusterException extends AppQingChengException {
    public static final String MSG = "集群中所有机器上都不存在指定的文件!请仔细检查文件是否存在!";

    public FileIsNotExistInClusterException() {
    }

    public FileIsNotExistInClusterException(String message) {
        super(message);
    }

    public FileIsNotExistInClusterException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileIsNotExistInClusterException(Throwable cause) {
        super(cause);
    }

    public FileIsNotExistInClusterException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
