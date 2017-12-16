package com.qingcheng.code.common.exception.hadoop.hdfs;

import com.qingcheng.code.common.exception.AppQingChengException;

/**
 * Created by liguohua on 16/10/2.
 */
public class HdfsFileHasAlreadyExistException extends AppQingChengException{
    public static final String MSG = "文件在hdfs中已经存在！请仔细检查hdfs文件路径！";

    public HdfsFileHasAlreadyExistException() {
    }

    public HdfsFileHasAlreadyExistException(String message) {
        super(message);
    }

    public HdfsFileHasAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public HdfsFileHasAlreadyExistException(Throwable cause) {
        super(cause);
    }

    public HdfsFileHasAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
