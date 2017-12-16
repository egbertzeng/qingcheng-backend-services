/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.user.service.bigdata;

import com.qingcheng.code.common.constant.AppConstants;
import com.qingcheng.code.common.properties.AppDynamic;
import com.qingcheng.code.common.util.basic.time.AppTimeUtils;
import com.qingcheng.code.system.bigdata.zookeeper.bigdata.runninginfo.AlluxioRuningInfo;
import com.qingcheng.code.system.bigdata.zookeeper.bigdata.runninginfo.HadoopRunningInfo;
import com.qingcheng.code.user.bean.DfsUrls;
import com.qingcheng.code.user.bean.FileStatuBean;
import com.qingcheng.code.user.bean.HDFSFileInfo;
import com.qingcheng.code.user.bean.MaterialFile;
import com.qingcheng.code.user.dao.bigdata.AlluxioHdfsDao;
import com.qingcheng.code.user.dao.bigdata.download.StreamSatisfier;
import com.qingcheng.code.user.dao.bigdata.download.StreamSatisfierUtil;
import com.qingcheng.code.user.service.mysql.DfsUrlsService;
import org.apache.hadoop.fs.FileStatus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class AlluxioHdfsService {
    private final static Logger logger = Logger.getLogger(AlluxioHdfsService.class);
    @Autowired
    private DfsUrlsService dfsUrlsService;

    /**
     * 根据zookeeper中的信息，即时决定使用的文件系统类型。
     * <p>
     * 如果alluxio可用,则使用alluxio.
     * 如果alluxio不可用，则使用hdfs
     *
     * @param fourceUseHdfs 强制使用hdfs
     * @return 文件系统类型。
     */
    private static AlluxioHdfsDao.FileSystemInfo getFileSystemInfo(boolean fourceUseHdfs) {
        if (AlluxioRuningInfo.isAlluxioCanEnble() && (!fourceUseHdfs)) {
            String[] pairs = AlluxioRuningInfo.getAlluxioactiveMasterUrl().trim().split(":");
            String master = pairs[0].trim();
            int port = Integer.parseInt(pairs[1].trim());
            return new AlluxioHdfsDao.FileSystemInfo(AlluxioHdfsDao.FileSystemType.ALLUXIO, master, port);
        } else {
            String master = HadoopRunningInfo.getCurrentRuningNamenode().trim();
            int port = Integer.parseInt(AppDynamic.getQingchengConfBigdataHadoopHdfsNamenodeRpcPort().trim());
            return new AlluxioHdfsDao.FileSystemInfo(AlluxioHdfsDao.FileSystemType.HDFS, master, port);
        }
    }

    /**
     * 将获取到的HDFS文件信息转化为Java对象列表
     *
     * @param dirStr 路径信息
     * @return 列表信息
     */
    public static List<HDFSFileInfo> makerPlaylistByURL(String dirStr) {
        ArrayList<HDFSFileInfo> allFileStatus = new ArrayList<>();
        List<FileStatus> fileStatus = AlluxioHdfsDao.listStatus(getFileSystemInfo(false), dirStr);
        for (FileStatus fs : fileStatus) {
            HDFSFileInfo hdfsFileInfo = new HDFSFileInfo();
            hdfsFileInfo.setPathSuffix(fs.getPath().getName().trim());
            allFileStatus.add(hdfsFileInfo);
        }
        return allFileStatus;
    }

    /**
     * 根据分布式文件系统的url ，获取文件的全路径
     * hdfs://qingcheng11:9000/qingcheng-->/qingcheng
     */
    private static String getFullPathByFs(String fp) {
        String[] ss = fp.split(AppConstants.SLASH);
        String fullPath = AppConstants.EMPTY;
        for (int i = 3; i < ss.length; i++) {
            fullPath = fullPath + AppConstants.SLASH + ss[i];
        }
        return fullPath;
    }

    /**
     * 从文件系统中获取输出流
     *
     * @param inputstreamFilepath 文件路径
     * @param os                  输出流
     */
    public void open(String inputstreamFilepath, OutputStream os) {
        AlluxioHdfsDao.open(getFileSystemInfo(false), inputstreamFilepath, os);
    }


    /**
     * 将office输出流转换为pdf输出流
     *
     * @param inputstreamFilepath 文件路径
     * @param os                  输出流
     */
    public void office2pdf(String inputstreamFilepath, String inputFormate, OutputStream os) {
        AlluxioHdfsDao.FileSystemInfo fileSystemInfo = getFileSystemInfo(false);
        AlluxioHdfsDao.office2pdf(fileSystemInfo, inputstreamFilepath, inputFormate, os);
        logger.info("###conver " + fileSystemInfo + inputstreamFilepath + " to pdf stream!");
    }

    /**
     * 获取指定路径下的所有文件的状态信息
     *
     * @param path 路径
     * @return 路径下文件的状态信息
     */
    public List<FileStatuBean> listStatus(String path) {
        List<FileStatuBean> list0 = new LinkedList<FileStatuBean>();
        //强制使用hdfs进行操作
        AlluxioHdfsDao.FileSystemInfo fsi = getFileSystemInfo(true);
        List<FileStatus> list = AlluxioHdfsDao.listStatus(fsi, path);
        LinkedList<DfsUrls> dfsUrlses = dfsUrlsService.getAllDfsUrls();
        for (FileStatus s : list) {
            String fp = getFullPathByFs(s.getPath().toString());
            //是否显示该对象
            boolean thisIscanDiplay = true;
            //改对象是否被收藏
            boolean thisIsFavorted = false;
            String trashURL = "";
            for (DfsUrls dfsUrlObj : dfsUrlses) {
                if (dfsUrlObj.getDfsUrl().equals(fp)) {
                    //处理收藏
                    if (dfsUrlObj.isMarkTask()) {
                        thisIsFavorted = true;
                    }
                    //处理显示
                    if ((dfsUrlObj.isTrashTask() || dfsUrlObj.isMoveTask())) {
                        thisIscanDiplay = false;
                        trashURL = fp;
                    }
                }
            }
            //添加对象
            String path0 = s.getPath().toString().trim();
            if (path0.startsWith(fsi.toString())) {
                path0 = path0.substring(fsi.toString().length());
            }
            list0.add(new FileStatuBean(path0, s.getPath().getName(), s.getLen(), s.isDirectory(), s.getReplication(), s.getBlockSize(), AppTimeUtils.getTimeString(s.getModificationTime(), AppTimeUtils.simpleDateFormat_1), AppTimeUtils.getTimeString(s.getAccessTime(), AppTimeUtils.simpleDateFormat_1), s.getPermission().toString(), s.getOwner(), s.getGroup(), thisIscanDiplay, thisIsFavorted, StreamSatisfierUtil.getProcessLength(path0), StreamSatisfierUtil.getProgressPercentStr(path0)));
        }
        return list0;
    }

    private String getAbsoultPathByPath(String path) {


        return AppConstants.EMPTY;
    }

    /**
     * 用于判断文件是否存在
     *
     * @param uri dfs文件路径
     * @return 文件是否存在
     */
    public boolean exists(String uri) {
        return AlluxioHdfsDao.exists(getFileSystemInfo(true), uri);
    }

    /**
     * 用于判断文件是否是目录
     *
     * @param uri dfs文件路径
     * @return 是否是目录
     */
    public boolean isDirectory(String uri) {
        return AlluxioHdfsDao.isDirectory(getFileSystemInfo(true), uri);
    }


    /**
     * 此方法用于删除文件
     *
     * @param url 文件路径
     * @return 删除文件是否成功
     */
    public boolean delete(String url) {
        //1.数据库中标记删除
        if (dfsUrlsService.dfsurlIsInTheTable(url)) {
            //已经存在，更新操作
            dfsUrlsService.updateTrashDfsUrls(url, 0);//取消删除
        }
        //2.文件系统中真正删除
        return AlluxioHdfsDao.delete(getFileSystemInfo(true), url);
    }

    /**
     * 此方法用于移动文件
     *
     * @param from 来源路径
     * @param to   目的路径
     */
    public boolean rename(String from, String to) {
        return AlluxioHdfsDao.rename(getFileSystemInfo(true), from, to);
    }

    /**
     * 此方法用于移动文件
     *
     * @param from 来源路径
     * @param to   目的路径
     */
    public void moveToDfs(String from, String to) {
        String activeMaster = getFileSystemInfo(true).getMaster();
        System.err.println("#####" + activeMaster);
        AlluxioHdfsDao.moveToDfs_ssh(activeMaster, from, to);
    }


    /**
     * 从文件系统中获取输出流（逐行读取）
     *
     * @param inputstreamFilepath 文件路径
     * @param os                  输出流
     */
    public void openLineByLine(String inputstreamFilepath, OutputStream os) {
        AlluxioHdfsDao.openLineByLine(getFileSystemInfo(true), inputstreamFilepath, os);
    }


    /**
     * download
     *
     * @param src  dfs路径
     * @param dist local路径
     */
    public void copyToLocalFile(String src, String dist) {
        AlluxioHdfsDao.copyToLocalFile(getFileSystemInfo(true), src, dist);
    }


    /**
     * 此方法用于创建文件夹
     *
     * @param path 文件路径
     * @return 创建文件夹是否成功
     */
    public boolean mkdirs(String path) {
        return AlluxioHdfsDao.mkdirs(getFileSystemInfo(true), path);
    }

    /**
     * 获取当前活动的nameNode
     *
     * @return 当前活动的nameNode
     */
    public String getActiveNameNodeInZk() {
        return getFileSystemInfo(true).getMaster();
    }

    /**
     * 获取处理进度
     */
    public StreamSatisfier getStreamSatisfierByUrl(String uri) {
        return StreamSatisfierUtil.getStreamSatisfierByUrl(uri);
    }

    /**
     * 压缩文件
     *
     * @param src  压缩源文件
     * @param dist 压缩生成的目标文件
     */
    public void compress(String src, String dist) {
        AlluxioHdfsDao.compress(getFileSystemInfo(true), src, dist);
    }

    /**
     * 解压文件
     *
     * @param src 解压源文件
     */
    public void decompress(String src) {
        AlluxioHdfsDao.decompress(getFileSystemInfo(true), src);
    }

    public List<MaterialFile> getMaterialFilesByPath(String path) {
        List<MaterialFile> materialFiles = new LinkedList<>();
        List<FileStatus> fileStatus = AlluxioHdfsDao.listStatus(getFileSystemInfo(false), path);
        for (FileStatus fs : fileStatus) {
            MaterialFile marterialFile = new MaterialFile();
            //文件名称
            String fileName = fs.getPath().getName().trim();
            marterialFile.setFileName(fileName);
            //文件路径
            String filePath = getFullPathByFs(fs.getPath().toString());
            marterialFile.setFilePath(filePath);
            //添加到列表
            materialFiles.add(marterialFile);
        }
        return materialFiles;
    }

}
