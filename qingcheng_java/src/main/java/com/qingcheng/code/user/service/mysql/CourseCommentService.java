package com.qingcheng.code.user.service.mysql;

import com.qingcheng.code.user.bean.CourseComment;
import com.qingcheng.code.user.dao.mysql.CourseCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

/**
 * Created by liguohua on 2017/6/9.
 */
@Service
public class CourseCommentService {
    @Autowired
    private CourseCommentMapper courseCommentMapper;

    /**
     * 根据课程的id，获取评论信息
     *
     * @return 评论信息
     */
    public LinkedList<CourseComment> getCourseCommentByCourseID(String courseID) {
        return courseCommentMapper.getCourseCommentByCourseID(courseID);
    }

    /**
     * 添加课程评论
     *
     * @param courseComment 课程评论
     */
    public void addCourseComment(CourseComment courseComment) {
        courseCommentMapper.addCourseComment(courseComment);
    }

    /**
     * 显示或隐藏评论
     *
     * @param courseComment 课程评论
     */
    public void hideOrShowCourseComment(CourseComment courseComment) {
        courseCommentMapper.hideOrShowCourseComment(courseComment);
    }
}
