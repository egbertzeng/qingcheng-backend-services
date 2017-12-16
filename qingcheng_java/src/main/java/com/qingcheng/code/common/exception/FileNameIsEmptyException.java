/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.common.exception;

/**
 * Created by liguohua on 16/9/11.
 */
public class FileNameIsEmptyException extends AppQingChengException {
    public  static  final String MSG="您输入的文件名称为空!请仔细检查文件名称!";
    public FileNameIsEmptyException() {
    }

    public FileNameIsEmptyException(String message) {
        super(message);
    }

    public FileNameIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileNameIsEmptyException(Throwable cause) {
        super(cause);
    }

    public FileNameIsEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
