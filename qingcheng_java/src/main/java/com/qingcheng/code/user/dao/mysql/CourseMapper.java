package com.qingcheng.code.user.dao.mysql;

import com.qingcheng.code.user.bean.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.LinkedList;


@Component
@Mapper
public interface CourseMapper {
    /**
     * 一、普通用户部分
     */
    String c = " CONCAT('%',#{k},'%') ";

    //通用的推荐数量
    String recommendLimitNum = " 8 ";

    //轮播中推荐数量
    String recommendForLoopLimitNum = " 20 ";

    //获取全部课程的限制数量
    String getLimitNum = " 80000 ";

    //过滤掉没有上线的课程。真正上线=类别上线+课程上线
    String categoryOnLineFilter = " AND categoryId IN (select distinct categoryId from Category where categoryIsOnLine!= 0)";

    //获取全部课程
    @Select("SELECT DISTINCT materialDirName,teacherEmail,courseID,courseName,courseSubName,courseUrl,courseDescription,coursePoster,coursePlayTimes,courseRecommendStart FROM Course WHERE courseOnLine=1 " + categoryOnLineFilter + "LIMIT" + getLimitNum)
    public LinkedList<Course> getAllCouses();

    //获取轮播组件中推荐的课程
    @Select("SELECT DISTINCT  materialDirName, teacherEmail,courseID,courseName,courseSubName,courseUrl,courseDescription,coursePoster,coursePlayTimes,courseRecommendStart FROM Course WHERE courseOnLine=1 AND courseIsRecommendedForLoop=1 " + categoryOnLineFilter + "LIMIT" + recommendForLoopLimitNum)
    public LinkedList<Course> getRecommendForLoopCouses();

    //获取根据播放量推荐的课程
    @Select("SELECT DISTINCT  materialDirName, teacherEmail,courseID, courseName, courseSubName,courseUrl,courseDescription,coursePoster,coursePlayTimes,courseRecommendStart FROM Course WHERE courseOnLine=1 " + categoryOnLineFilter + " ORDER BY coursePlayTimes LIMIT" + recommendLimitNum)
    public LinkedList<Course> getCousesOrderByPlayTimes();

    //获取根据推荐值推荐的课程
    @Select("SELECT DISTINCT  materialDirName, teacherEmail,courseID, courseName, courseSubName,courseUrl,courseDescription,coursePoster,coursePlayTimes,courseRecommendStart FROM Course  WHERE courseOnLine=1 AND courseIsRecommended=1 " + categoryOnLineFilter + "ORDER BY courseRecommendStart DESC  LIMIT" + recommendLimitNum)
    public LinkedList<Course> getCousesOrderByRecommendStart();

    //获取根据搜索条件检索到的课程
    @Select("SELECT DISTINCT  materialDirName, teacherEmail,courseID, courseName, courseSubName,courseUrl,courseDescription,coursePoster,coursePlayTimes,courseRecommendStart FROM Course WHERE courseOnLine=1" + categoryOnLineFilter + " AND ( courseName LIKE " + c + " OR courseDescription LIKE " + c + " OR  courseSubName LIKE " + c + ") ORDER BY coursePlayTimes DESC  LIMIT" + getLimitNum)
    public LinkedList<Course> getCousesBySearchKey(String k);

    //获取根据categoryId获得的课程
    @Select("SELECT DISTINCT  materialDirName, teacherEmail,courseID, courseName, courseSubName,courseUrl,courseDescription,coursePoster,coursePlayTimes,courseRecommendStart FROM Course  WHERE courseOnLine=1 AND  categoryId LIKE #{categoryId}" + categoryOnLineFilter + " ORDER BY coursePlayTimes DESC  LIMIT" + getLimitNum)
    public LinkedList<Course> getCousesBySearchCategoryid(String categoryId);


    /**
     * 课程的粉丝数量排行榜
     */
    @Select("SELECT DISTINCT" +
            " c.courseID," +
            " c.teacherEmail," +
            " c.courseName," +
            " c.courseSubName," +
            " c.courseUrl," +
            " c.courseDescription," +
            " c.coursePoster," +
            " c.coursePlayTimes," +
            " c.courseRecommendStart," +
            " c. materialDirName," +
            " COUNT(0) AS courseFollowerNumber" +
            " FROM Course c LEFT JOIN UserCourse uc ON c.courseID = uc.courseID" +
            " WHERE c.courseOnLine = 1 AND uc.favorite = 1 " +
            " AND uc.email IN (SELECT u.email FROM user u where u.role=0 AND u.activeStatus=1)" +
            " GROUP BY c.courseID" +
            " ORDER BY count(0) DESC" +
            " LIMIT 1000")
    public LinkedList<Course> getCousesOrderByCourseFollowerNumber();


    /**
     * 二、管理员部分
     */
    //获取全部课程
    @Select("SELECT DISTINCT *  FROM Course " + "LIMIT" + getLimitNum)
    public LinkedList<Course> getAllCouses4Admin();

    //更新课程信息
    @Update(" UPDATE Course  SET courseID=#{courseID},courseName=#{courseName},courseSubName=#{ courseSubName},courseUrl=#{courseUrl},courseDescription=#{courseDescription} ,coursePoster=#{coursePoster} ,  coursePlayTimes=#{coursePlayTimes} ,courseRecommendStart=#{courseRecommendStart},courseIsRecommended=#{courseIsRecommended} ,courseOnLine=#{courseOnLine} ,courseIsRecommendedForLoop=#{courseIsRecommendedForLoop} WHERE courseID=#{courseID}")
    public void updateCourse(Course course);

    //根据课程id删除课程
    @Delete("DELETE Course WHERE  courseID=#{courseID}")
    public void deleteCousesByCourseId(String courseID);


}
