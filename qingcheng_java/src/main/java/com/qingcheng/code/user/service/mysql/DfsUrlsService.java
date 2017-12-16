package com.qingcheng.code.user.service.mysql;

import com.qingcheng.code.user.bean.DfsUrls;
import com.qingcheng.code.user.dao.mysql.DfsUrlsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

/**
 * Created by liguohua on 05/01/2017.
 */
@Service
public class DfsUrlsService {
    @Autowired
    private DfsUrlsMapper dfsUrlsMapper;

    /**
     * 获取全部的URL
     *
     * @return 全部的URL
     */
    public LinkedList<DfsUrls> getAllDfsUrls() {
        return this.dfsUrlsMapper.getAllDfsUrls();
    }

    /**
     * 插入一条任务记录
     *
     * @param dfsUrl
     */
    public void insertDfsUrls(String dfsUrl) {
        this.dfsUrlsMapper.insertNeededDfsUrls(dfsUrl);
    }

    /**
     * 判断是否存在给定的URL
     * 个数=1存在
     * 个数=0不存在
     * 个数<0异常
     * 个数>0异常
     *
     * @param url 给定的URL
     * @return 是否存在给定的URL
     */
    public boolean dfsurlIsInTheTable(String url) {
        int count = this.dfsUrlsMapper.countDfsurlByUrl(url);
        boolean inTable = false;
        if (count == 1) {
            inTable = true;
        } else if (count == 0) {
            inTable = false;
        } else {
            new RuntimeException("系统错误，URL个数错误！" + count);
        }
        return inTable;
    }
     /*
       1. 删除
     */

    /**
     * 获取删除记录
     *
     * @return 删除记录
     */
    public LinkedList<String> getTrashTaskUrls() {
        return this.dfsUrlsMapper.getTrashTaskUrls();
    }


    /**
     * 更新删除记录
     *
     * @param dfsUrl    删除记录
     * @param trashTask 标记
     */
    public void updateTrashDfsUrls(String dfsUrl, int trashTask) {
        this.dfsUrlsMapper.updateTrashDfsUrls(dfsUrl, trashTask);
    }

    /**
     * 清空全部删除记录
     */
    public void cleanTrashDfsUrls() {
        this.dfsUrlsMapper.cleanTrashDfsUrls();
    }

     /*
       2. 剪切
     */

    /**
     * 获取剪切记录
     *
     * @return 剪切记录
     */
    public LinkedList<String> getCutTaskUrls() {
        return this.dfsUrlsMapper.getCutTaskUrls();
    }


    /**
     * 更新剪切记录
     *
     * @param dfsUrl   剪切记录
     * @param moveTask 标记
     */
    public void updateCutDfsUrls(String dfsUrl, int moveTask) {
        this.dfsUrlsMapper.updateCutDfsUrls(dfsUrl, moveTask);
    }

    /**
     * 清空全部剪切记录
     */
    public void cleanCutDfsUrls() {
        this.dfsUrlsMapper.cleanCutDfsUrls();
    }


     /*
       3. 收藏
     */

    /**
     * 获取收藏记录
     *
     * @return 收藏记录
     */
    public LinkedList<String> getFavoriteUrls() {
        return this.dfsUrlsMapper.getFavoriteUrls();
    }


    /**
     * 更新收藏记录
     *
     * @param dfsUrl   收藏记录
     * @param markTask 标记
     */
    public void updateFavoriteDfsUrls(String dfsUrl, int markTask) {
        this.dfsUrlsMapper.updateFavoriteDfsUrls(dfsUrl, markTask);
    }

    /**
     * 清空全部收藏记录
     */
    public void cleanFavoriteDfsUrls() {
        this.dfsUrlsMapper.cleanFavoriteDfsUrls();
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
    public LinkedList<String> getHistoryTaskUrls() {
        return this.dfsUrlsMapper.getHistoryTaskUrls();
    }


    /**
     * 更新历史记录
     *
     * @param dfsUrl      历史记录
     * @param historyTask 标记
     */
    public void updateHistoryTaskDfsUrls(String dfsUrl, int historyTask) {
        this.dfsUrlsMapper.updateHistoryTaskDfsUrls(dfsUrl, historyTask);
    }


    /**
     * 清空全部历史记录
     */
    public void cleanhistoryTaskDfsUrls() {
        this.dfsUrlsMapper.cleanhistoryTaskDfsUrls();
    }

}
