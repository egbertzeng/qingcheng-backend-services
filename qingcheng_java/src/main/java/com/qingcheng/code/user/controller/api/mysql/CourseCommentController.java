package com.qingcheng.code.user.controller.api.mysql;

import com.qingcheng.code.common.util.advance.filetype.FileType;
import com.qingcheng.code.user.bean.CourseComment;
import com.qingcheng.code.user.service.mysql.CourseCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.qingcheng.code.common.constant.AppRestUrls.QINGCHENG_SQL_ADD_COURSE_COMMENT;
import static com.qingcheng.code.common.constant.AppRestUrls.QINGCHENG_SQL_COURSE_COMMENT_GET_BY_COURSEID;
import static com.qingcheng.code.common.constant.AppRestUrls.QINGCHENG_SQL_HIDE_OR_SHOW_COURSE_COMMENT;
import static com.qingcheng.code.user.controller.util.DataUtil.composeResponseData;

/**
 * Created by liguohua on 2017/6/9.
 */
@RestController
public class CourseCommentController {
    @Autowired
    private CourseCommentService courseCommentService;

    /**
     * 根据课程的id，获取评论信息
     *
     * @return 评论信息
     */
    @RequestMapping(value = QINGCHENG_SQL_COURSE_COMMENT_GET_BY_COURSEID, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public Object getCourseCommentByCourseID(@RequestParam("courseID") String courseID) {
        Object data = courseCommentService.getCourseCommentByCourseID(courseID);
        return composeResponseData(data);
    }

    /**
     * 添加课程评论
     *
     * @param courseComment 课程评论
     */
    @RequestMapping(value = QINGCHENG_SQL_ADD_COURSE_COMMENT, method = RequestMethod.POST, produces = FileType.CONTENT_TYPE_JSON)
    public void addCourseComment(@RequestBody CourseComment courseComment) {
        courseCommentService.addCourseComment(courseComment);
    }

    /**
     * 显示或隐藏评论
     *
     * @param courseComment 课程评论
     */
    @RequestMapping(value = QINGCHENG_SQL_HIDE_OR_SHOW_COURSE_COMMENT, method = RequestMethod.POST, produces = FileType.CONTENT_TYPE_JSON)
    public void hideOrShowCourseComment(@RequestBody CourseComment courseComment) {
        courseCommentService.hideOrShowCourseComment(courseComment);
    }
}
