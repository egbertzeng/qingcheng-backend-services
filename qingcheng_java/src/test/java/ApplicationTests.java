//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.test.context.junit4.SpringRunner;
//
//
////@SpringApplicationConfiguration(classes = App.class)
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = App.class)
//public class ApplicationTests {
//
//    @Autowired
//    private JavaMailSender mailSender;
//
//    @Test
//    public void sendSimpleMail() throws Exception {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom("dyc87112@qq.com");
//        message.setTo("dyc87112@qq.com");
//        message.setSubject("主题：简单邮件");
//        message.setText("测试邮件内容");
//
//        mailSender.send(message);
//    }
//
//}