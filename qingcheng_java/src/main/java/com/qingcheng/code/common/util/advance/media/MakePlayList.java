/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.common.util.advance.media;

import java.io.File;
import java.util.LinkedList;

/**
 * Created by liguohua on 5/2/16.
 */
public class MakePlayList {
    public final static LinkedList<File> files = new LinkedList<>();

    public static LinkedList makePlayListByLocalDir(String dir) {
        File[] fs = new File(dir).listFiles();
        LinkedList<File> dirs = new LinkedList<>();
        for (File f : fs) {
            if (f.isFile()) {
                String fn = f.getName();
                if (fn.endsWith("mp4") || fn.endsWith("mkv")) {
                    files.add(f);
                }
            } else if (f.isDirectory()) {
                dirs.add(f);
            }
        }
        for (File d : dirs) {
            makePlayListByLocalDir(d.getAbsolutePath());
        }
        return files;
    }

}
