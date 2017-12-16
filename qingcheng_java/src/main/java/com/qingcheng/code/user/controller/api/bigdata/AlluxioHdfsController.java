/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.user.controller.api.bigdata;

import com.qingcheng.code.common.constant.AppConstants;
import com.qingcheng.code.common.constant.AppRestUrls;
import com.qingcheng.code.common.properties.AppDynamic;
import com.qingcheng.code.common.util.advance.filetype.FileType;
import com.qingcheng.code.common.util.advance.filetype.FileTypeUtils;
import com.qingcheng.code.common.util.basic.str.AppStrUtils;
import com.qingcheng.code.user.bean.FileStatuBean;
import com.qingcheng.code.user.bean.HDFSFileInfo;
import com.qingcheng.code.user.bean.VideoFile;
import com.qingcheng.code.user.dao.bigdata.download.StreamSatisfier;
import com.qingcheng.code.user.service.bigdata.AlluxioHdfsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import static com.qingcheng.code.common.constant.AppRestUrls.REST_URL_QINGCHENG_DFS_GET_MARTERIAL_BY_PATH;
import static com.qingcheng.code.user.controller.util.DataUtil.composeResponseData;


@RestController
@Transactional
public class AlluxioHdfsController {
    @Autowired
    private AlluxioHdfsService alluxioHdfsService;


/**
 * 一、用于网校的控制
 */
    /**
     * 获取指定路径下的所有文件的状态信息
     *
     * @param dir 路径
     * @return 路径下文件的状态信息
     */


    /**
     * 此方法是webplayer的使用的主要类,用于音乐,视频,图片流的处理.
     */
    @RequestMapping(value = AppRestUrls.REST_URL_courseFileOpneRestUrl, params = {AppRestUrls.REST_URL_courseFileOpneRestUrl_FilePath})
    protected void open(HttpServletRequest req, HttpServletResponse res) throws IOException {
        open(req, res, false);
    }

    protected void open(HttpServletRequest req, HttpServletResponse res, boolean needDownLoad) throws IOException {
        //1.设置输入流
        String inputstreamFilepath = req.getParameter(AppRestUrls.REST_URL_courseFileOpneRestUrl_FilePath);
        if (AppStrUtils.isEmptyStr(inputstreamFilepath)) {
            inputstreamFilepath = AppConstants.SLASH;
        }
        //2.设置输出流
        OutputStream os = res.getOutputStream();
        //2.1获取请求文件的类型信息
        String requestFileType = FileTypeUtils.getRequestFileType(inputstreamFilepath);
        //2.2判断是否是download
        if (needDownLoad) {
            res.setHeader("Content-Disposition", "attachment;filename=" + inputstreamFilepath);
            res.setContentType(FileTypeUtils.getContentTypeByFileType(requestFileType));
        }
        //2.3判断是否进行office2pdf转换
        if (FileTypeUtils.isNeedConvert2PdfStream(requestFileType)) {
            requestFileType = FileTypeUtils.getInputDocumentType(requestFileType);
            alluxioHdfsService.office2pdf(inputstreamFilepath, requestFileType, os);
        } else {
            alluxioHdfsService.open(inputstreamFilepath, os);
        }
    }
/**
 * 二、用于网盘的控制
 */

    /**
     * 此方法用于,根据HDFS目录中的视频文件,生成播放列表
     *
     * @param dir 存放course视频的dfs目录路径
     * @return course视频的播放列表
     */
    @RequestMapping(value = AppRestUrls.REST_URL_videoListOneCourseRestUrl)
    public Object  makerPlaylistByURL(@RequestParam(AppRestUrls.REST_URL_videoListOneCourseRestUrlPrara) String dir) {
        //处理请求目录
        if (dir == null) {
            return null;
        }
        dir = dir.trim();
        if (!dir.startsWith(AppConstants.URL_SPLITER)) {
            dir = AppConstants.URL_SPLITER + dir;
        }
        if (!dir.endsWith(AppConstants.URL_SPLITER)) {
            dir = dir + AppConstants.URL_SPLITER;
        }
        //获取HDFS文件系统中,指定目录下的文件信息
        List<HDFSFileInfo> files = AlluxioHdfsService.makerPlaylistByURL(dir);
        Integer id = 0;
        LinkedList<VideoFile> vedios = new LinkedList<>();
        for (HDFSFileInfo f : files) {
            String fileName = f.getPathSuffix();
            //生成播放列表
            if (fileName.endsWith(AppDynamic.getQingchengConfJavaApplicationMediaSupportVideoDataPrimaryType())) {
                //创建播放列表文件
                String videoName = fileName;
                String posterName = fileName.substring(0, fileName.length() - AppDynamic.getQingchengConfJavaApplicationMediaSupportImageDataPrimaryType().length()) + AppDynamic.getQingchengConfJavaApplicationMediaSupportImageDataPrimaryType();
                String vedioURL = dir + videoName;
                String posterURL = dir + posterName;
                VideoFile file = new VideoFile((id++).toString(), videoName, vedioURL, posterURL);
                //将文件加入到播放列表
                vedios.add(file);
            }
        }
        //按文件名称进行排序
        Collections.sort(vedios, new Comparator<VideoFile>() {
            @Override
            public int compare(VideoFile o1, VideoFile o2) {
                return o1.getFileName().compareTo(o2.getFileName());
            }
        });
        return composeResponseData(vedios);

    }


    @RequestMapping(value = AppRestUrls.REST_URL_wangpanListByDirRestUrl)
    public Map<String, List<FileStatuBean>> listStatus(@RequestParam(AppRestUrls.REST_URL_wangpanListByDirRestUrl_dir) String dir) {
        Map<String, List<FileStatuBean>> map = new HashMap<>();
        //如果没有指定路径，默认读取跟路径下的文件信息
        if (dir == null || AppConstants.EMPTY.equals(dir.trim())) {
            dir = AppConstants.SLASH;
        } else {
            if (!dir.startsWith(AppConstants.SLASH)) {
                dir = AppConstants.SLASH + dir;
            }
        }
        map.put("filestatus", alluxioHdfsService.listStatus(dir));
        return map;
    }

    /**
     * 用于判断文件是否存在
     *
     * @param uri dfs文件路径
     * @return 文件是否存在
     */
    @RequestMapping(value = AppRestUrls.REST_URL_wangpanIsThisUriExistRestUrl)
    public Map<String, Boolean> exists(@RequestParam(AppRestUrls.REST_URL_wangpanCommonPara_url) String uri) {
        Map<String, Boolean> map = new HashMap<>();
        map.put("filestatus", alluxioHdfsService.exists(uri));
        return map;
    }

    /**
     * 用于判断文件是否是目录
     *
     * @param url dfs文件路径
     * @return 是否是目录
     */
    @RequestMapping(value = AppRestUrls.REST_URL_wangpanUriIsDirRestUrl)
    public Map<String, Boolean> isDirectory(@RequestParam(AppRestUrls.REST_URL_wangpanCommonPara_url) String url) {
        Map<String, Boolean> map = new HashMap<>();
        map.put("filestatus", alluxioHdfsService.isDirectory(url));
        return map;
    }

    /**
     * 此方法用于删除文件（真删）
     *
     * @param url 文件路径
     * @return 删除文件是否成功
     */
    @RequestMapping(value = AppRestUrls.REST_URL_wangpanDeleteTrashUrlTrueRestUrl)
    public boolean delete(@RequestParam(AppRestUrls.REST_URL_wangpanCommonPara_url) String url) {
        return alluxioHdfsService.delete(url);
    }


    /**
     * 此方法用于移动文件(真移动)
     *
     * @param from 来源路径
     * @param to   目的路径
     */
    @RequestMapping(value = AppRestUrls.REST_URL_wangpanPasteCutUrlRestUrl)
    public void moveToDfs(@RequestParam(AppRestUrls.REST_URL_wangpanCommonPara_url) String from,
                          @RequestParam(AppRestUrls.REST_URL_wangpanCommonPara_targeturl) String to) {
        System.err.println("from=" + from);
        System.err.println("to=" + to);
        if (!from.equals(to)) {
            alluxioHdfsService.rename(from, to);
        }
    }


    /**
     * 从文件系统中获取输出流（逐行读取）
     */
    @RequestMapping(value = AppRestUrls.REST_URL_wangpanOpneLineByLineRestUrl, params = {AppRestUrls.REST_URL_courseFileOpneRestUrl_FilePath})
    public void openLineByLine(HttpServletRequest req, HttpServletResponse res) throws IOException {
        //1.设置输入流
        String inputstreamFilepath = req.getParameter(AppRestUrls.REST_URL_courseFileOpneRestUrl_FilePath);
        //2.设置输出流
        OutputStream os = res.getOutputStream();
        //2.1获取请求文件的类型信息
        String requestFileType = FileTypeUtils.getRequestFileType(inputstreamFilepath);
        alluxioHdfsService.openLineByLine(inputstreamFilepath, os);
    }

    /**
     * 此方法用于创建文件夹
     *
     * @param path 文件路径
     * @return 创建文件夹是否成功
     */
    @RequestMapping(value = AppRestUrls.REST_URL_wangpanMakeDirRestUrl)
    public boolean mkdirs(@RequestParam(AppRestUrls.REST_URL_wangpanCommonPara_url) String path) {
        System.err.println("ok##########" + path);
        return alluxioHdfsService.mkdirs(path);
    }

    /**
     * download
     *
     * @param src  dfs路径
     * @param dist local路径
     */
    @RequestMapping(value = AppRestUrls.REST_URL_wangpanFileDownloadRestUrl)
    public void copyToLocalFile(@RequestParam(AppRestUrls.REST_URL_wangpanCommonPara_url) String src, @RequestParam(AppRestUrls.REST_URL_wangpanCommonPara_targeturl) String dist) {
        System.err.println("ok##########");
        alluxioHdfsService.copyToLocalFile(src, dist);
    }


    /**
     * 获取当前活动的nameNode
     *
     * @return 当前活动的nameNode
     */
    @RequestMapping(value = AppRestUrls.REST_URL_wangpanGetActiveNamenodeRestUrl)
    public String getActiveNameNodeInZk() {
        return alluxioHdfsService.getActiveNameNodeInZk();
    }


    /**
     * 获取当前活动的nameNode的webui地址
     *
     * @return 当前活动的nameNode的webui地址
     */
    @RequestMapping(value = AppRestUrls.REST_URL_wangpanGetActiveNamenodeWebuiUrlRestUrl)
    public String getActiveNameNodeWebUrlInZk() {
        String url = AppConstants.HTTP_PROTOCOL_PREFIX + alluxioHdfsService.getActiveNameNodeInZk() + AppConstants.COLON + AppDynamic.getQingchengConfBigdataHadoopHdfsNamenodeHttpPort();
        return url;
    }

    /**
     * 获得当前的处理进度（压缩进度，解压进度）
     *
     * @param uri 文件路径
     * @return 处理进度信息
     */
    @RequestMapping(value = AppRestUrls.REST_URL_wangpanGetProcessProgressRestUrl)
    public StreamSatisfier getStreamSatisfierByUrl(@RequestParam(AppRestUrls.REST_URL_wangpanCommonPara_url) String uri) {
        return alluxioHdfsService.getStreamSatisfierByUrl(uri);
    }

    /**
     * 文件压缩操作
     *
     * @param src 源文件
     */
    @RequestMapping(value = AppRestUrls.REST_URL_wangpanCompressRestUrl)
    public String compress(@RequestParam("arg1") String src, @RequestParam("arg2") String compressFileFullPath) {
        alluxioHdfsService.compress(src, compressFileFullPath);
        return "compressFile";
    }


    /**
     * 文件解压操作
     *
     * @param src 源文件
     */
    @RequestMapping(value = AppRestUrls.REST_URL_wangpanDeCompressRestUrl)
    public String decompress(@RequestParam("arg1") String src) {
        alluxioHdfsService.decompress(src);
        return "decompressFile";
    }



    @RequestMapping(value = REST_URL_QINGCHENG_DFS_GET_MARTERIAL_BY_PATH,method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public Object  getMaterialFilesByPath(@RequestParam("path") String path) {
        return composeResponseData(alluxioHdfsService.getMaterialFilesByPath(path));
    }
}

	