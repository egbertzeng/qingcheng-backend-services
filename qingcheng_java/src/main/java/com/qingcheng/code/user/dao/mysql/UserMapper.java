/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.user.dao.mysql;

import com.qingcheng.code.user.bean.User;
import com.qingcheng.code.user.bean.UserParam;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.LinkedList;

import static com.qingcheng.code.user.dao.mysql.util.UserConst.USER_STUDETN;
import static com.qingcheng.code.user.dao.mysql.util.UserConst.USER_TEACHER;


@Component
@Mapper
public interface UserMapper {


    @Select("SELECT * FROM user")
    public LinkedList<User> findUserInfo();

    @Select("SELECT * FROM user WHERE  role=#{role}")
    public LinkedList<User> findUserInfos(String role);

    @Select("SELECT email,name,gender,photo,slogan,activeStatus,role FROM user WHERE activeStatus = 1 AND role=" + USER_TEACHER)
    public LinkedList<User> findAllAciveTeacher();

    @Select("SELECT * FROM user WHERE email=#{email}")
    public User findUserInfoByEmail(String email);

    @Select("SELECT * FROM user WHERE email=#{email} and activeStatus = 1")
    public User findActiveUserInfoByEmail(String email);

    @Select("SELECT * FROM user WHERE activeCode=#{activeCode}")
    public User findUserInfoByactiveCode(String activeCode);

    //    @Select("SELECT * FROM user WHERE name LIKE '%${value}%';")
//    public LinkedList<User> queryUserInfoByName(String name);
//
    @Insert("INSERT INTO user(email,name,password,activeStatus,activeCode)values(#{email},#{name},#{password},#{activeStatus},#{activeCode})")
    public void insertUser(User user);

    @Update(" UPDATE user SET name=#{name},password=#{password},activeStatus=#{activeStatus},activeCode=#{activeCode} WHERE  email=#{email}")
    public void updateUser(User user);

//    @Delete("DELETE FROM user WHERE email=#{email}")
//    public void deleteUserByEmail(String email);

//    @Update(" UPDATE user SET name=#{name},password=#{password},activeStatus=#{activeStatus},activeCode=#{activeCode} WHERE  email=#{email}")
//    @Options(useGeneratedKeys = true, keyProperty = "id")
//    public void updateUser(User user);

    @Update(" UPDATE user SET name=#{name},slogan=#{slogan},activeStatus=#{activeStatus},photo=#{photo} WHERE email=#{email}")
    public void updateUserByEmail(User user);

    /**
     * 用户粉丝数量排行榜
     */
    @Select("SELECT DISTINCT u.email," +
            "u.name," +
            "u.photo," +
            "u.slogan," +
            "u.gender," +
            "u.role," +
            "uu.relationStatus," +
            "COUNT(u.email) AS followerNumber" +
            " FROM user u LEFT JOIN UserUser uu ON u.email = uu.toEmail" +
            " WHERE u.activeStatus = 1 AND uu.relationStatus = 1 AND uu.relationType=#{relationType} AND u.role=#{role}" +
            " GROUP BY u.email" +
            " ORDER BY count(uu.toEmail) DESC" +
            " LIMIT 1000;")
    public LinkedList<User> getUserOrderByUserFollowerNumber(UserParam userParam);

    /**
     * 学生，关注课程数量的排行榜
     */
    @Select("SELECT DISTINCT u.email," +
            "  u.name," +
            "  u.photo," +
            "  u.slogan," +
            "  u.gender," +
            "  u.role," +
            " COUNT(u.email) AS followCourseNumber" +
            " FROM user u LEFT JOIN UserCourse uc ON u.email = uc.email" +
            " WHERE u.activeStatus = 1 AND u.role = " + USER_STUDETN + " AND uc.favorite=1" +
            " GROUP BY u.email" +
            " ORDER BY COUNT(uc.email) DESC" +
            " LIMIT 1000")
    public LinkedList<User> getUserOrderByUserFollowerCourseNumber();


    /**
     * 老师，发布课程数量的排行榜
     */
    @Select("SELECT DISTINCT" +
            "  u.email," +
            "  u.name," +
            "  u.photo," +
            "  u.slogan," +
            "  u.gender," +
            "  u.role," +
            " COUNT(u.email) AS publishCourseNumber" +
            " FROM user u LEFT JOIN Course c ON u.email = c.teacherEmail" +
            " WHERE u.activeStatus = 1 AND c.courseOnLine = 1 AND u.role = " + USER_TEACHER +
            " GROUP BY u.email" +
            " ORDER BY COUNT(c.teacherEmail) DESC" +
            " LIMIT 1000")
    public LinkedList<User> getUserOrderByUserPublishCourseNumber();


}
