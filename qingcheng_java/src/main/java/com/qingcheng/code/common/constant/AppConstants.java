/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.common.constant;

/**
 * Created by liguohua on 16/7/26.
 * 此类用于application常量的定义
 */
public interface AppConstants {
    //0.常用转义字符常量化
    String _TAB="\\t";
    //1.常用标点符号常量化
    String URL_SPLITER = "/";
    String SLASH = "/";
    String BLANK = " ";
    String EMPTY = "";
    String SEMICOLON = ";";
    String COLON = ":";
    String COMMA = ",";
    String DOTTO = ".";
    String UNDERSCORE_DASH = "_";


    //2.常用字符串常量化
    String UPPER_CASE_TRUE_STR = "TRUE";
    String LOWER_CASE_TRUE_STR = "true";
    String UPPER_CASE_FALSE_STR = "FALSE";
    String LOWER_CASE_FALSE_STR = "false";

    //3.常用协议常量化
    String HDFS_PROTOCOL_PREFIX = "hdfs://";
    String HTTP_PROTOCOL_PREFIX = "http://";
    String ALLUXIO_PROTOCOL_PREFIX = "alluxio://";
    //4.application常用名称常量化
    String HdfsImagesSequenceFileName = "$$$_images.seq";
    //5.Hadoop常用符号
    String HDFS_ROOT_PATH="/";

    //6.信息计量单位
    String INFO_BIT="bit";
    String INFO_KB="kb";
    String INFO_MB="M";
    String INFO_GB="G";
    String INFO_TB="T";
    String INFO_PB="P";
    String INFO_EB="E";
    String INFO_ZB="Z";
}
