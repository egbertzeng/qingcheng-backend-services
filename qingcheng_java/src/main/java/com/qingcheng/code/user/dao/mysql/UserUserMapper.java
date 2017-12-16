package com.qingcheng.code.user.dao.mysql;

import com.qingcheng.code.user.bean.User;
import com.qingcheng.code.user.bean.UserUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.LinkedList;


@Component
@Mapper
public interface UserUserMapper {
    /**
     * 根据email获取他所关注的用户。role = 2表示是教师，role = 0表示是学生
     */
    @Select("SELECT email,name,photo,gender,slogan,activeStatus,role FROM user  WHERE role=#{role} AND activeStatus = 1 AND email IN ( SELECT toEmail FROM UserUser WHERE relationStatus=1 AND fromEmail=#{fromEmail} AND relationType=#{relationType} )")
    LinkedList<User> getFavoriteUserByFromEmail(@Param(value = "fromEmail") String fromEmail, @Param(value = "role") Integer role, @Param(value = "relationType") Integer relationType);

    /**
     * 添加新的用户与用户之间的关系
     */
    @Insert("INSERT INTO UserUser(fromEmail,toEmail,relationType,relationStatus)values(#{fromEmail},#{toEmail},#{relationType},#{relationStatus})")
    public void insertUserUser(UserUser userUser);

    /**
     * 判断是否存在
     */
    @Select("SELECT COUNT(*) FROM UserUser WHERE fromEmail=#{fromEmail} AND toEmail=#{toEmail}")
    public int countUserUser(UserUser userUser);

    @Update("UPDATE UserUser SET fromEmail=#{fromEmail},toEmail=#{toEmail},relationType=#{relationType},relationStatus=#{relationStatus} WHERE fromEmail=#{fromEmail} AND toEmail=#{toEmail}")
    public void updateUserUser(UserUser userUser);

    @Delete("DELETE FROM UserUser WHERE fromEmail=#{fromEmail} AND toEmail=#{toEmail}")
    public void  deletUserUser(UserUser userUser);

}
