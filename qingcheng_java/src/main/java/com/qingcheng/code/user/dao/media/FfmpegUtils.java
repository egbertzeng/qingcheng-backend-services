package com.qingcheng.code.user.dao.media;

import com.qingcheng.code.common.util.advance.shell.LocalShellExecutor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liguohua on 2017/5/9.
 */
public class FfmpegUtils {
    /**
     * @param video
     * @param image
     */
    public static void makeOneImageByOneVideo(String video, String image) {
        // ffmpeg -i [video]  -ss 1 -vframes 1 -r 1 -ac 1 -ab 2 -s 960*400 -f  image2 [image]
        //1.拼接命令
        List<String> command = new ArrayList<>();
        command.add("ffmpeg");
        command.add("-i");
        command.add(video);
        command.add("-ss");
        command.add("1");
        command.add("-vframes");
        command.add("1");
        command.add(image);
        //2.执行命令
        //本地执行
        LocalShellExecutor.exexcutorShellCommondsWithResponse(command);
    }


    /**
     * 将mp4文件，复制出一个ts副本
     *
     * @param src  MP4文件
     * @param dist ts文件
     */
    public static void copyMp4ToTs(String src, String dist) {
        //ffmpeg -i src.mp4 -vcodec copy -acodec copy -vbsf h264_mp4toannexb dist.ts -y

        src = src.trim();
        dist = dist.trim();
        //1.拼接命令
        List<String> command = new ArrayList<>();
        command.add("ffmpeg");
        command.add("-i");
        command.add(src);
        command.add("-vcodec");
        command.add("copy");
        command.add("-acodec");
        command.add("copy");
        command.add("-vbsf");
        command.add("h264_mp4toannexb");
        command.add(dist);
        command.add("-y");
        //2.执行命令
        //本地执行
        LocalShellExecutor.exexcutorShellCommondsWithResponse(command);
    }

    /**
     * 将两个ts文件合并成一个MP4文件
     *
     * @param ts1 ts1
     * @param ts2 ts2
     * @param out mp4
     */
    public static void concatTwoTsToMp4(String ts1, String ts2, String out) {
        //ffmpeg -i "concat:ts1.ts|ts2.ts" -acodec copy -vcodec copy -absf aac_adtstoasc out.mp4 -y

        ts1 = ts1.trim();
        ts2 = ts2.trim();
        out = out.trim();
        //1.拼接命令
        List<String> command = new ArrayList<>();
        command.add("ffmpeg");
        command.add("-i");
        command.add("concat:" + ts1 + "|" + ts2);
        command.add("-acodec");
        command.add("copy");
        command.add("-vcodec");
        command.add("copy");
        command.add("-absf");
        command.add("aac_adtstoasc");
        command.add(out);
        command.add("-y");
        //2.执行命令
        //本地执行
        LocalShellExecutor.exexcutorShellCommondsWithResponse(command);
    }
}
