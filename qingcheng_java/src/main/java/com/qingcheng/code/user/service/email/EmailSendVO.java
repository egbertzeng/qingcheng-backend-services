package com.qingcheng.code.user.service.email;

import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * Created by liguohua on 2017/5/7.
 */
public class EmailSendVO {
    //邮件发送者
    private String from;
    //邮件接收者
    private String to;
    //邮件主题
    private String subject;
    //邮件模板model
    private Map<String, Object> emailTemplateModel = new HashedMap();
    //邮件模板路径
    private String emailTemplatePath;

    public EmailSendVO(String from, String to, String subject, String emailTemplatePath,Map<String, Object> emailTemplateModel) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.emailTemplateModel = emailTemplateModel;
        this.emailTemplatePath = emailTemplatePath;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Map<String, Object> getEmailTemplateModel() {
        return emailTemplateModel;
    }

    public void setEmailTemplateModel(Map<String, Object> emailTemplateModel) {
        this.emailTemplateModel = emailTemplateModel;
    }

    public String getEmailTemplatePath() {
        return emailTemplatePath;
    }

    public void setEmailTemplatePath(String emailTemplatePath) {
        this.emailTemplatePath = emailTemplatePath;
    }

    @Override
    public String toString() {
        return "EmailSendVO{" +
                "from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", subject='" + subject + '\'' +
                ", emailTemplateModel=" + emailTemplateModel +
                ", emailTemplatePath='" + emailTemplatePath + '\'' +
                '}';
    }
}
