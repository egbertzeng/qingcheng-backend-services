package com.qingcheng.code.user.service.mysql;

import com.qingcheng.code.common.constant.AppRestUrls;
import com.qingcheng.code.user.bean.User;
import com.qingcheng.code.user.service.email.EmailSendService;
import com.qingcheng.code.user.service.email.EmailSendVO;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

import static com.qingcheng.code.common.constant.AppRestUrls.REST_URL_APP_ACTIVE_P;

/**
 * Created by liguohua on 2017/5/6.
 */
@Service
public class ActiveAcountService {
    @Autowired
    EmailSendService emailSendService;

    //此方法用于向新注册用户发送激活邮件
    public void sendEmailToNewRegistUserForActiveAcount(User user) {
        String from = "2818461312@qq.com";
        String to = user.getEmail();
        String subject = user.getName() + "您好,青橙学院诚邀您激活新账号";
        String path = "email/RegistActiveEmailTemplate.html";
        Map<String, Object> model = new HashedMap();
        model.put("username", user.getName());
        model.put("email", user.getEmail());
        model.put("url", "http://localhost:8080" + AppRestUrls.REST_URL_APP_ACTIVE + "?" + REST_URL_APP_ACTIVE_P + "=" + user.getActiveCode());
        model.put("currentDate", new Date());
        EmailSendVO emailSendVO = new EmailSendVO(from, to, subject, path, model);
        emailSendService.emailSendService(emailSendVO);
    }
}
