package com.qingcheng.code.user.controller.api.mysql;

import com.qingcheng.code.common.constant.AppRestUrls;
import com.qingcheng.code.common.util.advance.filetype.FileType;
import com.qingcheng.code.user.dao.mysql.TeacherCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.qingcheng.code.user.controller.util.DataUtil.composeResponseData;


@RestController
@Transactional
public class TeacherCourseController {
    @Autowired
    private TeacherCourseMapper teacherCourseMapper;

    /**
     * 根据老师的email，获取老师所发布的课程
     */
    @RequestMapping(value = AppRestUrls.REST_URL_QINGCHENG_SQL_COURSE_GET_BY_TEACHER_EMAIL, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    Object getCousesByTeacherEmail(@RequestParam(value = "teacherEmail") String teacherEmail) {
        return composeResponseData(teacherCourseMapper.getCousesByTeacherEmail(teacherEmail));
    }

    /**
     * 根据courseID，获取老师的信息
     *
     * @param courseID courseID
     * @return 老师的信息
     */
    @RequestMapping(value = AppRestUrls.REST_URL_QINGCHENG_SQL_TEACHER_GET_BY_COURSEID, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    Object getTeacherByCourseId(@RequestParam(value = "courseID") String courseID) {
        return composeResponseData(teacherCourseMapper.getTeacherByCourseId(courseID));
    }


}
