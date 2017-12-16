package com.qingcheng.code.user.dao.bigdata;

import com.qingcheng.code.user.dao.bigdata.download.StreamSatisfierUtil;
import org.apache.hadoop.fs.ContentSummary;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;

import java.io.IOException;

public class HdfsAndAlluxioUtilsTest {
    //0.创建文件系统信息
    private static AlluxioHdfsDao.FileSystemInfo alluxio = new AlluxioHdfsDao.FileSystemInfo(AlluxioHdfsDao.FileSystemType.ALLUXIO, "qingcheng11", 19998);
    private static AlluxioHdfsDao.FileSystemInfo hdfs = new AlluxioHdfsDao.FileSystemInfo(AlluxioHdfsDao.FileSystemType.HDFS, "qingcheng12", 9000);


    public static void main00(String[] args) {


    }



    //解压测试
    public static void maind(String[] args) {

//        String src = "/qingcheng/mp3/xiaopingguo2.mp3.gz";//gzip
//        String src = "/qingcheng/mp3/xiaopingguo2.mp3.bz2";//bzip2
//        String src  = "/qingcheng/mp3/xiaopingguo2.mp3.deflate";//deflate
        String src = "/qingcheng/video2/yingyong_m0_720p.mp4.bz2";//default

        printProgress();
        AlluxioHdfsDao.decompress(hdfs, src);
    }

    //压缩测试
    public static void main000(String[] args) {

        String src = "/qingcheng/video2/yingyong_m0_720p.mp4";
//        String dist = "/qingcheng/mp3/xiaopingguo2.mp3.gz";//gzip
        String dist = "/qingcheng/video2/yingyong_m0_720p.mp4.bz2";//bzip2
//        String dist = "/qingcheng/mp3/xiaopingguo2.mp3.deflate";//deflate
//        String dist = "/qingcheng/video2/yingyong_m0_720p.mp4.default";//default


        printProgress();

        AlluxioHdfsDao.compress(hdfs, src, dist);

    }

    private static void printProgress() {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.err.println(StreamSatisfierUtil.map);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    //获取活动datanode
    public static void main2(String[] args) {
        DatanodeInfo[] datanodeInfos = AlluxioHdfsDao.getDecommissioningDataNodeStats(hdfs);
        for (DatanodeInfo info : datanodeInfos) {
            System.err.println(info);
        }
    }

    //获取 ContentSummary
    public static void main1(String[] args) {
//        ContentSummary contentSummary= AlluxioHdfsDao.getContentSummary(hdfs,"/qingcheng");
        ContentSummary contentSummary = AlluxioHdfsDao.getContentSummary(hdfs, "/qingcheng/mp3/xiaopingguo.mp3");
        System.err.println("DirectoryCount=" + contentSummary.getDirectoryCount());
        System.err.println("FileCount" + contentSummary.getFileCount());
        System.err.println("Length=" + contentSummary.getLength());
        System.err.println("Quota=" + contentSummary.getQuota());
        System.err.println("SpaceConsumed=" + contentSummary.getSpaceConsumed());
        System.err.println("SpaceQuota=" + contentSummary.getSpaceQuota());


    }

    //测试处理进度
    public static void main0(String[] args) throws IOException {
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.err.println(StreamSatisfierUtil.map);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        String spath = "/qingcheng/video/bingchuanshiji3.mp4";
        String dpath = "/Users/liguohua/Downloads/bingchuanshiji3.mp4";
        AlluxioHdfsDao.open2(hdfs, spath, dpath);
    }
}
