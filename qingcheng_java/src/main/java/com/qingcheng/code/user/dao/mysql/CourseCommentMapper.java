package com.qingcheng.code.user.dao.mysql;

import com.qingcheng.code.user.bean.CourseComment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.LinkedList;

/**
 * Created by liguohua on 2017/6/9.
 */
public interface CourseCommentMapper {
    /**
     * 根据课程的id，获取评论信息
     *
     * @return 评论信息
     */
    @Select("SELECT * FROM CourseComment WHERE courseID=#{courseID} AND commentCanDisplay=true ORDER BY commentTime DESC ")
    LinkedList<CourseComment> getCourseCommentByCourseID(String courseID);

    /**
     * 添加课程评论
     *
     * @param courseComment 课程评论
     */
    @Insert("INSERT INTO CourseComment(email,courseID,commentContent)values(#{email},#{courseID},#{commentContent})")
    void addCourseComment(CourseComment courseComment);

    /**
     * 显示或隐藏评论
     *
     * @param courseComment 课程评论
     */
    @Update("UPDATE CourseComment SET commentCanDisplay=#{commentCanDisplay} WHERE email=#{email} AND courseID=#{courseID} AND commentContent=#{commentContent} AND commentTime=#{commentTime}")
    void hideOrShowCourseComment(CourseComment courseComment);
}
