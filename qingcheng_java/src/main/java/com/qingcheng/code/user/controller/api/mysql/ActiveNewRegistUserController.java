package com.qingcheng.code.user.controller.api.mysql;

import com.qingcheng.code.common.constant.AppRestUrls;
import com.qingcheng.code.common.util.advance.filetype.FileType;
import com.qingcheng.code.user.service.mysql.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

import static com.qingcheng.code.common.constant.AppRestUrls.REST_URL_APP_ACTIVE_P;

/**
 * Created by liguohua on 2017/5/7.
 */
@Controller
public class ActiveNewRegistUserController {
    @Autowired
    UserService userService;

    @RequestMapping(value = AppRestUrls.REST_URL_APP_ACTIVE, method = RequestMethod.GET, produces = FileType.CONTENT_TYPE_JSON)
    public String activeNewUser(@RequestParam(REST_URL_APP_ACTIVE_P) String activeCode,Map<String,Object> map) {
        return userService.activeNewUser(activeCode, "functional/activeNewUserOk", map);
    }
}
