/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.common.exception;

/**
 * Created by liguohua on 16/9/11.
 */
public class AppQingChengException extends  RuntimeException {
    public AppQingChengException() {
    }

    public AppQingChengException(String message) {
        super(message);
    }

    public AppQingChengException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppQingChengException(Throwable cause) {
        super(cause);
    }

    public AppQingChengException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
