package com.qingcheng.code.user.service.mysql;

import com.qingcheng.code.user.bean.User;
import com.qingcheng.code.user.bean.UserParam;
import com.qingcheng.code.user.dao.mysql.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Map;

/**
 * Created by liguohua on 2017/5/4.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户
     */
    public User findActiveUserInfoByEmail(String email) {
        return userMapper.findActiveUserInfoByEmail(email);
    }

    /**
     * 将用户插入数据库
     *
     * @param user 用户
     */
    public void insertUser(User user) {
        //1.查询数据库中邮箱是否存在
        if (userMapper.findUserInfoByEmail(user.getEmail().trim()) != null) {
            throw new RuntimeException("error:email has exist");
        }
        userMapper.insertUser(user);
    }

    /**
     * 根据激活码查询用户
     *
     * @param activeCode 激活码
     * @return 用户
     */
    public User findUserInfoByactiveCode(String activeCode) {
        return userMapper.findUserInfoByactiveCode(activeCode);
    }

    /**
     * 更新用户
     *
     * @param user 用户
     */
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    /**
     * 根据激活码激活用户
     *
     * @param activeCode 激活码
     * @return 返回值
     */
    public String activeNewUser(String activeCode, String path, Map<String, Object> model) {
        System.out.println("1" + activeCode);
        path = path.trim();
        String msgKey = "msg";
        String msg = "";
        if ("".equals(activeCode) || activeCode == null) {
            msg = "用户激活失败！激活码不能为空！";
            model.put(msgKey, msg);
            return path;
        }
        //1.根据激活码查询用户
        User user = this.findUserInfoByactiveCode(activeCode.trim());
        //2.激活用户的状态
        if (user != null) {
            //激活操作
            user.setActiveCode("");
            user.setActiveStatus(true);
            this.updateUser(user);
            msg = "恭喜您!您的青橙账号：" + user.getEmail() + "已经激活。可以登陆使用了！";
            model.put(msgKey, msg);
            model.put("callBackUrl", "http://localhost:8000/login");
        } else {
            //激活失败
            msg = "根据激活码：" + activeCode + "查询不到用户，用户激活失败！";
            model.put(msgKey, msg);
        }
        return path;
    }

    public LinkedList<User> findAllAciveTeacher() {
        return userMapper.findAllAciveTeacher();
    }


    /**
     * 用户粉丝数量排行榜
     */
    public LinkedList<User> getUserOrderByUserFollowerNumber(UserParam userParam) {
        return userMapper.getUserOrderByUserFollowerNumber(userParam);
    }

    /**
     * 学生，关注课程数量的排行榜
     */
    public LinkedList<User> getUserOrderByUserFollowerCourseNumber() {
        return userMapper.getUserOrderByUserFollowerCourseNumber();
    }

    /**
     * 老师，发布课程数量的排行榜
     */
    public LinkedList<User> getUserOrderByUserPublishCourseNumber() {
        return userMapper.getUserOrderByUserPublishCourseNumber();
    }

    /**
     * 获取所有用户的信息
     */
    public LinkedList<User> findUserInfos(String role) {
        return userMapper.findUserInfos(role);
    }

    /**
     * 更新用户
     */
    public void updateUserByEmail(User user) {
        userMapper.updateUserByEmail(user);
    }

    /**
     * 根据email获取用户信息
     */
    public User findUserInfoByEmail(String email) {
        return userMapper.findUserInfoByEmail(email);
    }
}
