package com.qingcheng.code.user.dao.mysql;

import com.qingcheng.code.user.bean.DfsUrls;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
@Mapper
public interface DfsUrlsMapper {
    /**
     * 获取全部的URL
     *
     * @return 全部的URL
     */
    @Select("select dfsUrl,trashTask,markTask,downloadTask,copyTask,moveTask from DfsUrls")
    public LinkedList<DfsUrls> getAllDfsUrls();

    /**
     * 插入一条记录
     *
     * @param dfsUrl 一条记录
     */
    @Insert("insert into DfsUrls(dfsUrl,trashTask,markTask,downloadTask,copyTask,moveTask)values(#{dfsUrl},0,0,0,0,0)")
    public void insertNeededDfsUrls(String dfsUrl);


    /**
     * 获取给定的URL在表中出现的个数
     *
     * @param url 给定的URL
     * @return 给定的URL在表中的个数
     */
    @Select("select count(dfsUrl) from DfsUrls WHERE dfsUrl=#{url}")
    public int countDfsurlByUrl(String url);


    @Insert("insert into DfsUrls(dfsUrl,trashTask,markTask,downloadTask,copyTask,moveTask)values(#{dfsUrl},#{trashTask},#{markTask},#{downloadTask},#{copyTask},#{moveTask})")
    public void insertDfsUrls(DfsUrls dfsUrl);

    @Delete("DELETE FROM DfsUrls WHERE dfsUrl=#{url}")
    public void deleteDfsUrls(String url);

    @Delete("DELETE FROM DfsUrls WHERE trashTask=false AND markTask=false AND downloadTask=false AND copyTask=false AND moveTask=false")
    public void pureDfsUrls();

    @Delete("DELETE FROM DfsUrls")
    public void cleanDfsUrls();

    /*
       1. 删除
     */

    /**
     * 获取标记为删除的URL
     *
     * @return 标记为删除的URL
     */
    @Select("select dfsUrl from DfsUrls WHERE trashTask=1")
    public LinkedList<String> getTrashTaskUrls();

    /**
     * 更新删除记录
     *
     * @param dfsUrl    删除记录
     * @param trashTask 标记
     */
    @Update("UPDATE DfsUrls SET trashTask=#{1} WHERE dfsUrl=#{0}")
    public void updateTrashDfsUrls(String dfsUrl, int trashTask);

    /**
     * 清空全部删除记录
     */
    @Update("UPDATE DfsUrls SET trashTask=0")
    public void cleanTrashDfsUrls();


    /*
       2. 剪切
     */

    /**
     * 获取剪切记录
     *
     * @return 剪切记录
     */
    @Select("select dfsUrl from DfsUrls WHERE moveTask=1")
    public LinkedList<String> getCutTaskUrls();


    /**
     * 更新剪切记录
     *
     * @param dfsUrl   剪切记录
     * @param moveTask 标记
     */
    @Update("UPDATE DfsUrls SET moveTask=#{1} WHERE dfsUrl=#{0}")
    public void updateCutDfsUrls(String dfsUrl, int moveTask);


    /**
     * 清空全部剪切记录
     */
    @Update("UPDATE DfsUrls SET moveTask=0")
    public void cleanCutDfsUrls();

    /*
       3. 收藏
     */

    /**
     * 获取收藏记录
     *
     * @return 收藏记录
     */
    @Select("select dfsUrl from DfsUrls WHERE markTask=1")
    public LinkedList<String> getFavoriteUrls();


    /**
     * 更新收藏记录
     *
     * @param dfsUrl   收藏记录
     * @param markTask 标记
     */
    @Update("UPDATE DfsUrls SET markTask=#{1} WHERE dfsUrl=#{0}")
    public void updateFavoriteDfsUrls(String dfsUrl, int markTask);


    /**
     * 清空全部收藏记录
     */
    @Update("UPDATE DfsUrls SET markTask=0")
    public void cleanFavoriteDfsUrls();


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
    @Select("select dfsUrl from DfsUrls WHERE historyTask=1")
    public LinkedList<String> getHistoryTaskUrls();


    /**
     * 更新历史记录
     *
     * @param dfsUrl   历史记录
     * @param historyTask 标记
     */
    @Update("UPDATE DfsUrls SET historyTask=#{1} WHERE dfsUrl=#{0}")
    public void updateHistoryTaskDfsUrls(String dfsUrl, int historyTask);


    /**
     * 清空全部历史记录
     */
    @Update("UPDATE DfsUrls SET historyTask=0")
    public void cleanhistoryTaskDfsUrls();





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


    /**
     * 获取下载记录
     *
     * @return 下载记录
     */
    @Select("select dfsUrl from DfsUrls WHERE downloadTask=1")
    public LinkedList<String> getDownloadTaskUrls();

    /**
     * 获取复制记录
     *
     * @return 标记复制记录
     */
    @Select("select dfsUrl from DfsUrls WHERE copyTask=1")
    public LinkedList<String> getCopyTaskUrls();


}
