package com.qingcheng.code.user.dao.mysql;

import com.qingcheng.code.user.bean.Course;
import com.qingcheng.code.user.bean.User;
import com.qingcheng.code.user.bean.UserCourse;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

@Component
@Mapper
public interface UserCourseMapper {
    /**
     * 获取email所关注的课程
     */
    @Select("select * from Course  where  courseID in (select courseID from UserCourse where favorite = 1 AND email= #{ email })")
    LinkedList<Course> getCousesByUserEmail4Favorite(String email);

    /**
     * 获取email所浏览的历史记录
     */
    @Select("select * from Course  where  courseID in (select courseID from UserCourse where email= #{ email })")
    LinkedList<Course> getCousesByUserEmail4History(String email);

    /**
     * 关注该课程的所有用户,要过滤掉管理员
     */
    @Select("SELECT email,name,gender,photo,slogan,activeStatus,role FROM user  WHERE role = 0 AND email IN (SELECT email FROM UserCourse WHERE courseID= #{ courseID })")
    LinkedList<User> getUserByUserCourseId(String courseID);

    /**
     * 查看用户关注的表中是否已经存在记录
     */
    @Select("select count(*) from UserCourse where email=#{email} AND courseID=#{courseID}")
    int courseHasExistInFavoriteTable(UserCourse userCourse);

    /**
     * 用于插入用户关注的信息
     */
    @Insert("insert into UserCourse (email,courseID,favorite) values(#{email},#{courseID},#{favorite})")
    void addCouses2UserFavorite(UserCourse userCourse);

    /**
     * 用于更新用户关注的课程
     */
    @Update("update UserCourse set favorite=#{favorite} where email=#{email} AND courseID=#{courseID}")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void updateCouses2UserFavorite(UserCourse userCourse);


    String deleteCondition=" WHERE courseID = #{courseID} AND email=#{email}";
    @Delete("DELETE FROM UserCourse "+deleteCondition)
    void deletCourse2UserFavorite(UserCourse userCourse);
}
