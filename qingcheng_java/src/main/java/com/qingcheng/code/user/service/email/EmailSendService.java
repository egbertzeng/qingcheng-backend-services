package com.qingcheng.code.user.service.email;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

/**
 * Created by liguohua on 2017/5/7.
 * 此类用于发送各种email
 */
@Component
public class EmailSendService {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    public void emailSendService(EmailSendVO emailSendVO) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            //邮件发送者
            helper.setFrom(emailSendVO.getFrom());
            //邮件接收者
            helper.setTo(emailSendVO.getTo());
            //邮件主题
            helper.setSubject(emailSendVO.getSubject());
            //邮件模板路径
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate(emailSendVO.getEmailTemplatePath());
            //邮件数据
            String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, emailSendVO.getEmailTemplateModel());
            helper.setText(text, true);
            //发送邮件
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (MalformedTemplateNameException e) {
            e.printStackTrace();
        } catch (TemplateNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}
