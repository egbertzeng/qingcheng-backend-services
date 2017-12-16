/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.user.bean;

/**
 * Created by liguohua on 4/27/16.
 */
public class HDFSFileInfo {
    private String pathSuffix;
    private String type;

    public String getPathSuffix() {
        return pathSuffix;
    }

    public void setPathSuffix(String pathSuffix) {
        this.pathSuffix = pathSuffix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "HDFSFileInfo{" +
                "pathSuffix='" + pathSuffix + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
