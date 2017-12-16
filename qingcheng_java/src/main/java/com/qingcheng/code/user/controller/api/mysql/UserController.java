/*
 * Copyright (c) 2016. 云業集團-青橙科技
 * Copyright © 2016 - 2016.  QingCheng Inc. All Rights Reserved
 */

package com.qingcheng.code.user.controller.api.mysql;

import com.qingcheng.code.common.configration.filters.JwtTokenService;
import com.qingcheng.code.common.constant.AppRestUrls;
import com.qingcheng.code.common.util.advance.filetype.FileType;
import com.qingcheng.code.common.util.basic.str.UuidUtil;
import com.qingcheng.code.user.bean.User;
import com.qingcheng.code.user.bean.UserParam;
import com.qingcheng.code.user.service.mysql.ActiveAcountService;
import com.qingcheng.code.user.service.mysql.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.io.UnsupportedEncodingException;

import static com.qingcheng.code.common.constant.AppRestUrls.*;
import static com.qingcheng.code.user.controller.util.DataUtil.composeResponseData;

@RestController
@Transactional
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    ActiveAcountService emailService;

    @RequestMapping(value = AppRestUrls.REST_URL_APP_LOGIN, method = RequestMethod.POST, produces = FileType.CONTENT_TYPE_JSON)
    public Object login(@RequestBody User loginUser) throws ServletException {
        System.err.println("******::::" + loginUser);
        //check input user
        String email = loginUser.getEmail().trim();
        String password = loginUser.getPassword().trim();
        if ("".equals(email) || "".equals(password)) {
            throw new ServletException("Please fill in username and password");
        }
        //user find check
        User user = userService.findActiveUserInfoByEmail(loginUser.getEmail().trim());
        if (user == null) {
            throw new ServletException("User is not found.");
        }
        //double check
        if (!email.equals(user.getEmail()) || !password.equals(user.getPassword())) {
            throw new ServletException("User's password is not right");
        }

        //数据脱敏
        user.setPassword("");
        user.setId(0);
        //create token
        String token = JwtTokenService.createJwtToken(email, 864000000, "user", user);
        //return data
        return composeResponseData(token);
    }

    @RequestMapping(value = AppRestUrls.REST_URL_APP_REGIST, method = RequestMethod.POST, produces = FileType.CONTENT_TYPE_JSON)
    public void registNewUser(@RequestBody User user) throws UnsupportedEncodingException {
        //1.向数据库中插入数据
        user.setActiveCode(UuidUtil.getUuid256());
        user.setActiveStatus(false);
        userService.insertUser(user);
        //2.发送邮件
        emailService.sendEmailToNewRegistUserForActiveAcount(user);
    }


    /**
     * 根据邮箱获取已经激活的用户
     *
     * @return
     */

    @RequestMapping(value = AppRestUrls.REST_URL_QINGCHENG_FIND_ACCOUNT_BY_EMAIL, method = RequestMethod.POST, produces = FileType.CONTENT_TYPE_JSON)
    public User findActiveUserInfoByEmail(@RequestBody User user) {
        String email = user.getEmail();
        if ("".equals(email) || email == null) {
            throw new RuntimeException("邮箱不能为空，你输入的邮箱为空！");
        }

        User user0 = userService.findActiveUserInfoByEmail(email);
        //删除敏感信息
        user0.setPassword("");
        user0.setActiveCode("");
        return user0;
    }

    /**
     * 用于提供,根据查询所有已经激活的老师
     *
     * @return 所有已已经激活的老师
     */
    @RequestMapping(value = AppRestUrls.REST_URL_QINGCHENG_GET_ALL_ACTIVE_TEACHER, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public Object findAllAciveTeacher() {
        return composeResponseData(userService.findAllAciveTeacher());
    }

    /**
     * 用户粉丝数量排行榜
     */
    @RequestMapping(value = REST_URL_QINGCHENG_SQL_GET_USER_FOLLOWER_ORDER_LIST, method = RequestMethod.POST, produces = FileType.CONTENT_TYPE_JSON)
    public Object getUserOrderByUserFollowerNumber(@RequestBody UserParam userParam) {
        return composeResponseData(userService.getUserOrderByUserFollowerNumber(userParam));
    }

    /**
     * 学生，关注课程数量的排行榜
     */
    @RequestMapping(value = REST_URL_QINGCHENG_SQL_GET_USER_FOLLOW_COURSE_ORDER_LIST, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public Object getUserOrderByUserFollowerCourseNumber() {
        return composeResponseData(userService.getUserOrderByUserFollowerCourseNumber());
    }

    /**
     * 老师，发布课程数量的排行榜
     */
    @RequestMapping(value = REST_URL_QINGCHENG_SQL_GET_USER_PUBLISH_COURSE_ORDER_LIST, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public Object getUserOrderByUserPublishCourseNumber() {
        return composeResponseData(userService.getUserOrderByUserPublishCourseNumber());
    }


    /**
     * 获取所有用户的信息
     */
    @RequestMapping(value = REST_URL_QINGCHENG_SQL_ADMIN_GET_ALL_USER, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public Object findUserInfos(@RequestParam(value = "role") String role) {
        return composeResponseData(userService.findUserInfos(role));
    }

    /**
     * 更新用户
     */
    @RequestMapping(value = REST_URL_QINGCHENG_SQL_ADMIN_UPDATE_USER, method = RequestMethod.POST, produces = FileType.CONTENT_TYPE_JSON)
    public Object updateUserByEmail(@RequestBody User user) {
        userService.updateUserByEmail(user);
        return composeResponseData("ok");
    }

    /**
     * 根据email获取用户信息
     */
    @RequestMapping(value = REST_URL_QINGCHENG_SQL_GET_USER_BY_EMAIL, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public Object findUserInfoByEmail(@RequestParam(value = "email")  String email) {
        System.err.println( email);
        return composeResponseData(userService.findUserInfoByEmail( email));
    }


}
