package com.qingcheng.code.user.service.media;

import com.qingcheng.code.user.dao.media.FfmpegUtils;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Created by liguohua on 2017/5/9.
 */
@Service
public class FfmpegService {
    private static String videoSuffix = ".mp4";
    private static String tempVideoSuffix = ".ts";
    private static String imageSuffix = ".png";

    public static void convertDir2Course(String prefixTs, String dir, boolean needComposeTsFile) {
        convertDir2Course(prefixTs, dir, true,needComposeTsFile);
    }

    /**
     * 此方法用于，根据生成DIR中所有mp4文件所对应的png文件
     *
     * @param dir              指定的目录
     * @param deleteOriginFile 是否清除原有的图像文件和ts临时文件
     */
    public static void convertDir2Course(String prefixTs, String dir, boolean deleteOriginFile, boolean needComposeTsFile) {
        dir = dir.trim();
        File file = new File(dir);
        File[] fs = file.listFiles();
        for (File f : fs) {
            if (f.isFile()) {
                if (f.getName().endsWith(videoSuffix)) {
                    //1.原视频文件路径
                    String vedio = f.getAbsolutePath().trim();
                    String videoNameWithoutSuffix = vedio.substring(0, vedio.length() - videoSuffix.length());

                    //2.处理课程需要的图像
                    String image = videoNameWithoutSuffix + imageSuffix;
                    processCourseImage(deleteOriginFile, image, vedio);
                    if (needComposeTsFile) {
                        //3.生成临时的ts文件
                        String ts = videoNameWithoutSuffix + tempVideoSuffix;
                        processTempTsFile(deleteOriginFile, ts, vedio);
                        //4.合成最终需要的mp4文件
                        String mp4 = videoNameWithoutSuffix + tempVideoSuffix;
                        FfmpegUtils.concatTwoTsToMp4(prefixTs, mp4, vedio);
                        //5.删除临时ts文件
                        deleteExistFile(deleteOriginFile, ts);
                    }
                }
            } else if (f.isDirectory()) {
                convertDir2Course(prefixTs, f.getAbsolutePath(), deleteOriginFile, needComposeTsFile);
            }
        }
    }

    /**
     * 此方法用于，处理临时ts文件
     *
     * @param deleteOriginFile
     * @param ts
     * @param vedio
     */

    private static void processTempTsFile(boolean deleteOriginFile, String ts, String vedio) {
        deleteExistFile(deleteOriginFile, ts);
        FfmpegUtils.copyMp4ToTs(vedio, ts);
    }

    /**
     * 此方法用于，处理生成课程所需要的图片
     *
     * @param deleteOriginFile
     * @param image
     * @param vedio
     */
    private static void processCourseImage(boolean deleteOriginFile, String image, String vedio) {
        deleteExistFile(deleteOriginFile, image);
        FfmpegUtils.makeOneImageByOneVideo(vedio, image);
    }

    /**
     * 此方法用于，删除已经存在的文件
     *
     * @param deleteOriginFile
     * @param file
     */

    private static void deleteExistFile(boolean deleteOriginFile, String file) {
        File imf = new File(file);
        if (imf.exists() && deleteOriginFile) {
            imf.delete();
        }
    }


    public static void copyMp4ToTs(String file) {
        //视频文件的绝对路径
        File f = new File(file.trim());
        String fap = f.getAbsolutePath().trim();
        //ts文件路径
        String fileNameWithoutSuffix = fap.substring(0, fap.length() - videoSuffix.length());
        String dist = fileNameWithoutSuffix + tempVideoSuffix;
        FfmpegUtils.copyMp4ToTs(fap, dist);
    }
}
