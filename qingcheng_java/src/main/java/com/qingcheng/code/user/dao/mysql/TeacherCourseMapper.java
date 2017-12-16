package com.qingcheng.code.user.dao.mysql;

import com.qingcheng.code.user.bean.Course;
import com.qingcheng.code.user.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.LinkedList;


@Component
@Mapper
public interface TeacherCourseMapper {
    /**
     * 根据老师的email，获取老师所发布的课程
     */
    @Select("SELECT * FROM Course  WHERE courseOnLine=1 AND teacherEmail=#{teacherEmail}")
    LinkedList<Course> getCousesByTeacherEmail(String teacherEmail);

    /**
     * 根据courseID，获取老师的信息
     */
    @Select("SELECT email,name,gender,photo,slogan,activeStatus,role FROM user  WHERE role =2 AND activeStatus = 1 AND email = (SELECT teacherEmail FROM Course WHERE courseID= #{ courseID })")
    User getTeacherByCourseId(String courseID);

}
