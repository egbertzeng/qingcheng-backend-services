/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.common.exception;

/**
 * Created by liguohua on 16/9/11.
 */
public class FilePathIsEmptyException extends AppQingChengException {
    public  static  final String MSG="您输入的文件路径为空!请仔细检查文件路径!";
    public FilePathIsEmptyException() {
    }

    public FilePathIsEmptyException(String message) {
        super(message);
    }

    public FilePathIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public FilePathIsEmptyException(Throwable cause) {
        super(cause);
    }

    public FilePathIsEmptyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
