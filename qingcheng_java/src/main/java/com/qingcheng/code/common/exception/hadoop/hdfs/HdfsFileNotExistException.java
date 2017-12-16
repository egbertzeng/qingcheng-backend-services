package com.qingcheng.code.common.exception.hadoop.hdfs;

import com.qingcheng.code.common.exception.AppQingChengException;

/**
 * Created by liguohua on 16/10/2.
 */
public class HdfsFileNotExistException extends AppQingChengException{
    public static final String MSG = "hdfs不存在指定的文件!请仔细检查文件是否存在!";

    public HdfsFileNotExistException() {
    }

    public HdfsFileNotExistException(String message) {
        super(message);
    }

    public HdfsFileNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public HdfsFileNotExistException(Throwable cause) {
        super(cause);
    }

    public HdfsFileNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
