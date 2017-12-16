/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.common.util.basic.str;

import com.qingcheng.code.common.constant.AppConstants;
import com.qingcheng.code.common.constant.SysConstants;
import com.qingcheng.code.common.exception.FileNameIsEmptyException;
import com.qingcheng.code.common.exception.FilePathIsEmptyException;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by liguohua on 16/8/22.
 */
public class AppStrUtils {

    public static final String ZNODE_PATH_SEPARATOR_REGEX = "/";
    public static final String ZNODE_NAME_SEPARATOR_REGEX = "_";
    public static final String PROPERTIES_FIELD_SEPARATOR_REGEX = "\\.";


    /**
     * 将属性值转化成znodePath
     *
     * @param propertieField 属性值
     * @return znodepath
     */
    public static String convertPropertyFieldToZnodePath(String propertieField) {
        String znodePath = propertieField.replaceAll(PROPERTIES_FIELD_SEPARATOR_REGEX, ZNODE_PATH_SEPARATOR_REGEX);
        //添加开头处的'/'
        if (!znodePath.startsWith(ZNODE_PATH_SEPARATOR_REGEX)) {
            znodePath = ZNODE_PATH_SEPARATOR_REGEX + znodePath;
        }
        //去掉结尾处的'/'
        if (znodePath.endsWith(ZNODE_PATH_SEPARATOR_REGEX)) {
            znodePath = znodePath.substring(0, znodePath.length() - 1);
        }
        return znodePath;
    }


    /**
     * 将属性值转化成znodePath
     *
     * @param znodePath 普通字符串
     * @return znodepath
     */
    public static String convertStringToZnodePath(String znodePath) {
        //添加开头处的'/'
        if (!znodePath.startsWith(ZNODE_PATH_SEPARATOR_REGEX)) {
            znodePath = ZNODE_PATH_SEPARATOR_REGEX + znodePath;
        }
        //去掉结尾处的'/'
        if (znodePath.endsWith(ZNODE_PATH_SEPARATOR_REGEX)) {
            znodePath = znodePath.substring(0, znodePath.length() - 1);
        }
        return znodePath;
    }

    /**
     * 将属性值转化成znodePath
     *
     * @param propertieField 属性值
     * @return znodepath
     */
    public static String convertPropertyFieldToZnodeName(String propertieField) {
        String znodePath = propertieField.replaceAll(PROPERTIES_FIELD_SEPARATOR_REGEX, ZNODE_NAME_SEPARATOR_REGEX);
        //刪除开头处的'_'
        if (znodePath.startsWith(ZNODE_NAME_SEPARATOR_REGEX)) {
            znodePath = znodePath.substring(1, znodePath.length());
        }
        //去掉结尾处的'_'
        if (znodePath.endsWith(ZNODE_PATH_SEPARATOR_REGEX)) {
            znodePath = znodePath.substring(0, znodePath.length() - 1);
        }
        return znodePath;
    }

    public static String getFirstNotZeroStr(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != '0') {
                s = s.substring(i, s.length());
                break;
            }
        }
        return s;
    }

    public static String getFilePath(String path) {
        //prefix
        if (!path.startsWith(SysConstants.FILE_SEPARATOR)) {
            path = SysConstants.FILE_SEPARATOR + path;
        }
        //suffix
        if (!path.endsWith(SysConstants.FILE_SEPARATOR)) {
            path = path + SysConstants.FILE_SEPARATOR;
        }

        return path;
    }

    public static String[] getStringArrayFromList(List<String> list) {
        String[] arr = new String[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    public static String[] splitStringWithSemicolon(String str) {
        return str.trim().split(AppConstants.SEMICOLON);
    }

    public static String[] splitStringWithComma(String str) {
        return str.trim().split(AppConstants.COMMA);
    }

    public static String getStringWithSemicolon(Collection<String> collection) {
        StringBuffer sb = new StringBuffer();
        for (String s : collection) {
            sb.append(s.trim());
            sb.append(AppConstants.SEMICOLON);
        }
        return sb.toString().trim();
    }

    public static String getStringWithComma(Collection<String> collection) {
        StringBuffer sb = new StringBuffer();
        for (String s : collection) {
            sb.append(s.trim());
            sb.append(AppConstants.COMMA);
        }
        return sb.toString().trim();
    }

    /**
     * @param hostsStr
     * @return
     */
    public static List<String> getHostListFromStringWithComma(String hostsStr) {
        String[] hosts = splitStringWithComma(hostsStr);
        List<String> list = new LinkedList<>();
        char bs = '[';
        char ms = '-';
        char es = ']';
        for (String s : hosts) {
            s = s.trim();
            int i = s.indexOf(bs);
            int j = s.indexOf(ms);
            int k = s.indexOf(es);
            if (0 < i && i < j && j < k) {
                String prefix = s.substring(0, i).trim();
                Long startNum = Long.parseLong(s.substring(i + 1, j).trim());
                Long endNum = Long.parseLong(s.substring(j + 1, k).trim());
                Long temp = 0l;
                if (endNum < startNum) {
                    temp = endNum;
                    endNum = startNum;
                    startNum = temp;
                }
                while (startNum <= endNum) {
                    s = prefix + startNum;
                    if (!list.contains(s)) {
                        list.add(s);
                    }
                    startNum++;
                }

            } else {
                if (!list.contains(s)) {
                    list.add(s);
                }
            }
        }
        return list;
    }

    /**
     * 判定字符串是否为空
     *
     * @param str 需要判定的字符串
     * @return 是否为空
     */
    public static boolean isWhiteStr(String str) {
        if (str != null) {
            return isEmptyStr(str.trim());
        }
        return true;
    }

    /**
     * 判定字符串是否为空
     *
     * @param str 需要判定的字符串
     * @return 是否为空
     */
    public static boolean isEmptyStr(String str) {
        if (str == null || AppConstants.EMPTY.equals(str)) {
            return true;
        }
        return false;
    }

    /**
     * 获取文件的绝对路径，根据文件路径和名称，会自动处理文件名和文件路径中的'/'
     *
     * @param path
     * @param fileName
     * @return
     */
    public static String getFileAbsolutePathByPathAndName(String path, String fileName) {
        return filePathDoer(path) + fileNameDoer(fileName);
    }

    /**
     * 根据文件的绝对路径，获取文件名称
     *
     * @param fileAbsolutePath 文件的绝对路径
     * @return 文件名称
     */
    public static String getFileNameByFileAbsolutePath(String fileAbsolutePath) {
        String fileName = fileAbsolutePath.substring(fileAbsolutePath.lastIndexOf(SysConstants.FILE_SEPARATOR) + 1);
        return fileNameDoer(fileName);
    }

    /**
     * 根据文件的绝对路径，获取文件路径
     *
     * @param fileAbsolutePath 文件的绝对路径
     * @return 文件路径
     */
    public static String getFilePathByFileAbsolutePath(String fileAbsolutePath) {
        String filePath = fileAbsolutePath.substring(0, fileAbsolutePath.lastIndexOf(SysConstants.FILE_SEPARATOR));
        return filePathDoer(filePath);
    }

    /**
     * 处理文件路径,所有文件路径必须在开头和结尾处使用'/'
     * 合法路径诸如:"/bigdata/software/"
     *
     * @param filePath 处理前的path
     * @return 处理后的path
     */
    public static String filePathDoer(String filePath) {
        //如果路径为空,直接返回异常
        if (isEmptyStr(filePath)) {
            throw new FilePathIsEmptyException(FilePathIsEmptyException.MSG);
        }
        //添加开头处的'/'
        if (!filePath.startsWith(SysConstants.FILE_SEPARATOR)) {
            filePath = SysConstants.FILE_SEPARATOR + filePath;
        }
        //添加结尾处的'/'
        if (!filePath.endsWith(SysConstants.FILE_SEPARATOR)) {
            filePath = filePath + SysConstants.FILE_SEPARATOR;
        }
        return filePath;
    }

    /**
     * 处理文件名称,所有文件名称必须去掉开头和结尾处的'/'
     * 合法路径诸如:"oceans.png"
     *
     * @param fileName 处理前的fileName
     * @return 处理后的fileName
     */
    public static String fileNameDoer(String fileName) {
        //如果路径为空,直接返回异常
        if (isEmptyStr(fileName)) {
            throw new FileNameIsEmptyException(FileNameIsEmptyException.MSG);
        }
        //去掉开头处的'/'
        if (fileName.startsWith(SysConstants.FILE_SEPARATOR)) {
            fileName = fileName.substring(fileName.indexOf(SysConstants.FILE_SEPARATOR) + 1).trim();

        }
        //去掉结尾处的'/'
        if (fileName.endsWith(SysConstants.FILE_SEPARATOR)) {
            fileName = fileName.substring(0, fileName.length() - 1).trim();

        }
        return fileName;
    }
}
