///*
// * Copyright (c) 2016. 云業集團-青橙科技
// * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
// */
//
//package com.qingcheng.code.common.util.advance.media;
//
//
//import com.qingcheng.code.common.util.advance.shell.LocalShellExecutor;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by liguohua on 5/1/16.
// */
//public class Ffmpeg {
//
//    public static void clearImagesByDir(String dir) {
//        makeImagesByDir0(dir, true);
//    }
//
//    public static void makeImagesByDir(String dir) {
//        makeImagesByDir0(dir, true);
//    }
//
//    private static void makeImagesByDir0(String dir, boolean clearImages) {
//        File file = new File(dir);
//        File[] fs = file.listFiles();
//        for (File f : fs) {
//            if (f.isFile()) {
//                String fn = f.getName();
//                if (fn.endsWith("mp4")) {
//                    String vedio = f.getAbsolutePath().trim();
//                    String image = vedio.substring(0, vedio.length() - 4) + ".png";
//                    File f0 = new File(image);
//                    if (f0.exists()) {
//                        f0.delete();
//                    }
//                    if (!clearImages) {
//                        makeOneImageByOneVideo(vedio, image);
//                    }
//                }
//            } else if (f.isDirectory()) {
//                makeImagesByDir0(f.getAbsolutePath(), clearImages);
//            }
//        }
//    }
//
//    private static String makeOneImageByOneVideo(String video, String image) {
//        // ffmpeg -i [video]  -ss 1 -vframes 1 -r 1 -ac 1 -ab 2 -s 960*400 -f  image2 [image]
//        //1.拼接命令
//        List<String> command = new ArrayList<String>();
//        command.add("ffmpeg");
//        command.add("-i");
//        command.add(video);
//        command.add("-ss");
//        command.add("1");
//        command.add("-vframes");
//        command.add("1");
//        command.add(image);
//        //2.执行命令
//        return LocalShellExecutor.exexcutorShellCommondsWithResponse(command);
//    }
//}
