package com.qingcheng.code.user.controller.api.mysql;

import com.qingcheng.code.common.constant.AppRestUrls;
import com.qingcheng.code.common.util.advance.filetype.FileType;
import com.qingcheng.code.user.bean.User;
import com.qingcheng.code.user.bean.UserUser;
import com.qingcheng.code.user.dao.mysql.UserUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static com.qingcheng.code.user.controller.util.DataUtil.composeResponseData;


@RestController
@Transactional
public class UserUserController {
    @Autowired
    private UserUserMapper userUserMapper;

    /**
     * 根据email获取他所关注的老师。
     *
     * @param formEmail formEmail
     * @return 所关注的老师。
     */
    @RequestMapping(value = AppRestUrls.REST_URL_QINGCHENG_SQL_COURSE_GET_USER_BY_FROMEMAIL, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    Object  getFavoriteTeacherByFromEmail(
            @RequestParam(value = "formEmail") String formEmail,
            @RequestParam(value = "relationType") Integer relationType,
            @RequestParam(value = "role") Integer role) {
        return composeResponseData(userUserMapper.getFavoriteUserByFromEmail(formEmail, role, relationType));
    }

    /**
     * 添加新的用户与用户之间的关系
     */
    @RequestMapping(value = AppRestUrls.REST_URL_QINGCHENG_UPDATE_USER_RELATION, method = RequestMethod.POST, produces = FileType.CONTENT_TYPE_JSON)
    public void insertUserUser(@RequestBody UserUser userUser) {
        //input check
        if (userUser.getFromEmail() == "undefined") {
            return;
        }
        System.err.println(userUser);
        //input action
        if (userUser.getRelationStatus()) {
            if (userUserMapper.countUserUser(userUser) == 0) {
                //insert
                userUserMapper.insertUserUser(userUser);
            }
        } else {
            //drop table
            userUserMapper.deletUserUser(userUser);
        }

    }
}
