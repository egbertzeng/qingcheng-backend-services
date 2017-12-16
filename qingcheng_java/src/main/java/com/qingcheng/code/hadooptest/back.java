/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 *//*


package com.qingcheng.code.user.dao.hadoop;

*/
/**
 * Created by liguohua on 16/7/29.
 *//*


import com.qingcheng.code.common.constant.AppConstants;
import com.qingcheng.code.common.properties.AppDynamic;
import com.qingcheng.code.system.bigdata.zookeeper.service.ZookeeperUtil;
import com.qingcheng.code.user.bean.HDFSFileInfo;
import com.qingcheng.code.user.dao.alluxioandhdfs.AlluxioRuningInfo;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.apache.hadoop.io.SequenceFile.createWriter;

@Component
public class back {
    private final static Logger logger = Logger.getLogger(back.class);

    //Hadoop相关的属性
    private static String CurrentRuningNamenode = null;
    private static Configuration conf = null;
    private static String HDFSAddress = null;
    private static FileSystem fs = null;

    */
/**
     * 用于初始化Hadoop相关的静态属性
     *//*

    static {
        conf = new Configuration();
        CurrentRuningNamenode = ZookeeperUtil.getActiveNamenodeHostname();
        HDFSAddress = setCurrentRuningNamenode(CurrentRuningNamenode);
    }

    */
/**
     * 此方法用于,当zookeeper中的activeNamenode发生变化时,重新设置HDFS的activeNamenode
     *
     * @param currentRuningNamenode
     * @return
     *//*

    public static String setCurrentRuningNamenode(String currentRuningNamenode) {
        CurrentRuningNamenode = currentRuningNamenode;
        HDFSAddress = (AppConstants.HDFS_PROTOCOL_PREFIX + CurrentRuningNamenode.trim() + AppConstants.COLON + AppDynamic.getQingchengConfBigdataHadoopHdfsNamenodeRpcPort()).trim();
        setFileSystem();
        AlluxioRuningInfo.isAlluxioIsEnble();
        return HDFSAddress;
    }

    public static String getCurrentRuningNamenode() {
        return CurrentRuningNamenode;
    }

    */
/**
     * 此方法用于,当zookeeper中的activeNamenode发生变化时,重新获取HDFS的FileSystem实例
     *
     * @return
     *//*

    private static void setFileSystem() {
        conf.addResource("classpath:/resources/bigdata/core-site.xml");
        conf.addResource("classpath:/resources/bigdata/hdfs-site.xml");
        conf.set("fs.defaultFS", HDFSAddress);
        try {
            fs = FileSystem.get(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getHDFSAddress() {
        return HDFSAddress;
    }

    */
/**
     * 将获取到的HDFS文件信息转化为Java对象列表
     *
     * @param dirStr
     * @return
     *//*

    public List<HDFSFileInfo> makerPlaylistByURL(String dirStr) {

        ArrayList<HDFSFileInfo> allFileStatus = new ArrayList<>();
        Path dirPaht = new Path(dirStr);

        try {
            Path seqFilePath = new Path(dirStr + AppConstants.HdfsImagesSequenceFileName);
            if (fs.exists(seqFilePath)) {

            } else {
                logger.info("this path hive no ");
                FileStatus[] fileStatus = fs.listStatus(dirPaht);
                for (FileStatus fs : fileStatus) {
                    HDFSFileInfo hdfsFileInfo = new HDFSFileInfo();
                    hdfsFileInfo.setPathSuffix(fs.getPath().getName().trim());
                    allFileStatus.add(hdfsFileInfo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allFileStatus;
    }

    */
/**
     * 将制定目录下的图片归档为一个sequenceFile
     *
     * @param inputPahtStr
     * @param seqFilePathStr
     * @throws IOException
     *//*

    public void makeSequencefIleByPath(String inputPahtStr, String seqFilePathStr) throws IOException {
        Path seqFilePath = new Path(inputPahtStr);
        Path inputPaht = new Path(seqFilePathStr);

        //如果图片归档文件已经存在,则删除之
        if (fs.exists(seqFilePath)) {
            fs.delete(seqFilePath, true);
        }

        //输入路径：文件夹
        FileStatus[] files = fs.listStatus(inputPaht);
        Text key = new Text();
        Text value = new Text();
        //输出路径：文件
        SequenceFile.Writer writer = createWriter(fs, conf, seqFilePath, key.getClass(), value.getClass());
        InputStream in = null;
        byte[] buffer = null;
        for (int i = 0; i < files.length; i++) {
            FileStatus fileStatus = files[i];
            Path filePath = fileStatus.getPath();
            String fileName = filePath.getName();
            if (fileName.endsWith(".png")) {
                long fileLength = files[i].getLen();
                key.set(fileName);
                in = fs.open(filePath);
                buffer = new byte[(int) fileLength];
                IOUtils.readFully(in, buffer, 0, buffer.length);
                value.set(buffer);
                IOUtils.closeStream(in);
                writer.append(key, value);
            }
        }
        IOUtils.closeStream(writer);
    }

    */
/**
     * 获取sequenceFile所包含的所有文件名称
     *
     * @param seqFilePath
     * @return
     * @throws IOException
     *//*

    public List<String> listSequcenFileContentFiles(Path seqFilePath) throws IOException {
        LinkedList<String> fileNames = new LinkedList<>();
        SequenceFile.Reader reader = new SequenceFile.Reader(conf, SequenceFile.Reader.file(seqFilePath));
        Text key = new Text();
        Text value = new Text();
        while (reader.next(key, value)) {
            fileNames.add(key.toString());
        }
        IOUtils.closeStream(reader);
        return fileNames;
    }

    */
/**
     * 根据文件名称,获取sequenceFile中的文件内容
     *
     * @param seqFilePathStr
     * @param fileName
     * @return
     * @throws IOException
     *//*

    public byte[] readSequcenFileContentFileByFilename(String seqFilePathStr, String fileName) throws IOException {
        Path seqFilePath = new Path(seqFilePathStr);
        SequenceFile.Reader reader = new SequenceFile.Reader(conf, SequenceFile.Reader.file(seqFilePath));
        Text key = new Text();
        Text value = new Text();
        while (reader.next(key, value)) {
            if (fileName.equals(key.toString())) {
                return value.getBytes();
            }
        }
        IOUtils.closeStream(reader);
        return null;
    }



}*/
