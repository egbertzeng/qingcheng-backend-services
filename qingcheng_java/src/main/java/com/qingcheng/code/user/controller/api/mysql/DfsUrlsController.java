/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.user.controller.api.mysql;

import com.qingcheng.code.common.constant.AppConstants;
import com.qingcheng.code.common.constant.AppRestUrls;
import com.qingcheng.code.common.util.advance.filetype.FileType;
import com.qingcheng.code.user.bean.DfsUrls;
import com.qingcheng.code.user.service.mysql.DfsUrlsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;

@RestController
@Transactional
public class DfsUrlsController {
    @Autowired
    private DfsUrlsService dfsUrlsService;

    @RequestMapping(value = "/qingcheng/sql/DfsUrls/list/all", method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public LinkedList<DfsUrls> getAllDfsUrls() {
        return dfsUrlsService.getAllDfsUrls();
    }


    /*
       1. 删除
     */

    /**
     * 获取标记为删除的URL
     *
     * @return 标记为删除的URL
     */
    @RequestMapping(value = AppRestUrls.REST_URL_wangpanGetTrashUrlRestUrl, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public LinkedList<String> getTrashTaskUrls() {
        LinkedList<String> list = this.dfsUrlsService.getTrashTaskUrls();
        return list;
    }

    /**
     * 添加删除记录
     *
     * @param url 删除记录
     */
    @RequestMapping(AppRestUrls.REST_URL_wangpanInsertTrashUrlRestUrl)
    public void insertOrUpdateTrashedDfsurl(@RequestParam(value = AppRestUrls.REST_URL_wangpanCommonPara_url) String url) {
        if (this.dfsUrlsService.dfsurlIsInTheTable(url)) {
            //已经存在，更新操作
            this.dfsUrlsService.updateTrashDfsUrls(url, 1);//标记为删除
        } else {
            //还不存在，插入操作
            //1.插入
            this.dfsUrlsService.insertDfsUrls(url);
            //2.更新
            this.dfsUrlsService.updateTrashDfsUrls(url, 1);//标记为删除
        }
    }

    /**
     * 取消删除记录
     *
     * @param url 删除记录
     */
    @RequestMapping(AppRestUrls.REST_URL_wangpanDeleteTrashUrlRestUrl)
    public boolean resetTrashedDfsurl(@RequestParam(value = AppRestUrls.REST_URL_wangpanCommonPara_url) String url) {
        if (this.dfsUrlsService.dfsurlIsInTheTable(url)) {
            //已经存在，更新操作
            this.dfsUrlsService.updateTrashDfsUrls(url, 0);//取消删除
        }
        return true;
    }

    /**
     * 清空全部删除记录
     */
    @RequestMapping(AppRestUrls.REST_URL_wangpanCleanTrashRestUrl)
    public boolean cleanTrashDfsUrls() {
        this.dfsUrlsService.cleanTrashDfsUrls();
        return true;
    }



    /*
       2. 剪切
     */

    /**
     * 获取剪切记录
     *
     * @return 剪切记录
     */
    @RequestMapping(value = AppRestUrls.REST_URL_wangpanGetCutUrlRestUrl, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public LinkedList<String> getCutTaskUrls() {
        LinkedList<String> list = this.dfsUrlsService.getCutTaskUrls();
        return list;
    }

    /**
     * 添加剪切记录
     *
     * @param url 剪切记录
     */
    @RequestMapping(AppRestUrls.REST_URL_wangpanInsertCutUrlRestUrl)
    public void insertOrUpdateCutDfsurl(@RequestParam(value = AppRestUrls.REST_URL_wangpanCommonPara_url) String url) {
        if (this.dfsUrlsService.dfsurlIsInTheTable(url)) {
            //已经存在，更新操作
            this.dfsUrlsService.updateCutDfsUrls(url, 1);//标记为剪切
        } else {
            //还不存在，插入操作
            //1.插入
            this.dfsUrlsService.insertDfsUrls(url);
            //2.更新
            this.dfsUrlsService.updateCutDfsUrls(url, 1);//标记为删除
        }
    }

    /**
     * 取消剪切记录
     *
     * @param url 剪切记录
     */
    @RequestMapping(AppRestUrls.REST_URL_wangpanReplayCutUrlRestUrl)
    public boolean resetCutDfsurl(@RequestParam(value = AppRestUrls.REST_URL_wangpanCommonPara_url) String url) {
        if (this.dfsUrlsService.dfsurlIsInTheTable(url)) {
            //已经存在，更新操作
            this.dfsUrlsService.updateCutDfsUrls(url, 0);//取消剪切
        }
        return true;
    }


    /*
       3. 收藏
     */

    /**
     * 获取收藏记录
     *
     * @return 收藏记录
     */
    @RequestMapping(value = AppRestUrls.REST_URL_wangpanGetFavoriteUrlRestUrl, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public LinkedList<String> getFavoriteUrls() {
        return this.dfsUrlsService.getFavoriteUrls();
    }


    /**
     * 添加收藏记录
     *
     * @param url 收藏记录
     */
    @RequestMapping(AppRestUrls.REST_URL_wangpanInsertFavoriteUrlRestUrl)
    public void insertOrUpdateFavoriteDfsurl(@RequestParam(value = AppRestUrls.REST_URL_wangpanCommonPara_url) String url) {
        if (this.dfsUrlsService.dfsurlIsInTheTable(url)) {
            //已经存在，更新操作
            this.dfsUrlsService.updateFavoriteDfsUrls(url, 1);//标记为收藏
        } else {
            //还不存在，插入操作
            //1.插入
            this.dfsUrlsService.insertDfsUrls(url);
            //2.更新
            this.dfsUrlsService.updateFavoriteDfsUrls(url, 1);//标记为收藏
        }
    }

    /**
     * 取消收藏记录
     *
     * @param url 收藏记录
     */
    @RequestMapping(AppRestUrls.REST_URL_wangpanDeleteFavoriteUrlRestUrl)
    public boolean resetFavoriteDfsurl(@RequestParam(value = AppRestUrls.REST_URL_wangpanCommonPara_url) String url) {
        if (this.dfsUrlsService.dfsurlIsInTheTable(url)) {
            //已经存在，更新操作
            this.dfsUrlsService.updateFavoriteDfsUrls(url, 0);//取消删除
        }
        return true;
    }

    /////////////////////////////
    /////////////////////////////
    /////////////////////////////
    /////////////////////////////
    /////////////////////////////
    /////////////////////////////
    /////////////////////////////
    /////////////////////////////
    /////////////////////////////
    /////////////////////////////

     /*
       4. 历史
     */

    /**
     * 获取历史记录
     *
     * @return 历史记录
     */

    @RequestMapping(value = AppRestUrls.REST_URL_wangpanGetHistoryUrlRestUrl, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public LinkedList<String> getHistoryTaskUrls() {
        return this.dfsUrlsService.getHistoryTaskUrls();
    }

    /**
     * 添加历史记录
     *
     * @param url 历史记录
     */
    @RequestMapping(AppRestUrls.REST_URL_wangpanInsertHistoryUrlRestUrl)
    public void insertOrUpdateHistoryDfsurl(@RequestParam(value = AppRestUrls.REST_URL_wangpanCommonPara_url) String url) {
        if (AppConstants.SLASH.equals(url)) {
            //根目录不需要插入历史
            return;
        }
        if (this.dfsUrlsService.dfsurlIsInTheTable(url)) {
            //已经存在，更新操作
            this.dfsUrlsService.updateHistoryTaskDfsUrls(url, 1);//标记为历史记录
        } else {
            //还不存在，插入操作
            //1.插入
            this.dfsUrlsService.insertDfsUrls(url);
            //2.更新
            this.dfsUrlsService.updateHistoryTaskDfsUrls(url, 1);//标记为历史记录
        }
    }

    /**
     * 取消历史记录
     *
     * @param url 历史记录
     */
    @RequestMapping(AppRestUrls.REST_URL_wangpanDeleteHistoryUrlRestUrl)
    public boolean resetHistoryDfsurl(@RequestParam(value = AppRestUrls.REST_URL_wangpanCommonPara_url) String url) {
        if (this.dfsUrlsService.dfsurlIsInTheTable(url)) {
            //已经存在，更新操作
            this.dfsUrlsService.updateHistoryTaskDfsUrls(url, 0);//取消历史记录
        }
        return true;
    }
}
