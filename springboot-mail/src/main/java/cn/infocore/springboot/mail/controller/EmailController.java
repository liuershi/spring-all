package cn.infocore.springboot.mail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Random;

/**
 * @Author wei.zhang@infocore.cn
 * @Date 2020/12/18 15:23
 * @Description 发送邮件的controller
 */
@RestController
@RequestMapping("mail")
public class EmailController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    private static final String TO = "wei.zhang@infocore.cn";

    /**
     * 发送简单的邮件
     *
     * @return
     */
    @GetMapping("simple")
    public String sendSimpleMail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            // 邮件的发送地址
            message.setFrom(from);
            // 邮件的接收地址
            message.setTo(TO);
            // 设置邮件标题
            message.setSubject("测试邮件");
            message.setText("使用Spring Boot发送简单邮件。");
            mailSender.send(message);
            return "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 发送HTML格式的邮件
     *
     * @return
     */
    @GetMapping("htmlMail")
    public String sendHtmlMail() {
        MimeMessage message;

        try {
            message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(TO);
            helper.setSubject("这是一封HTML格式的邮件");
            String content = "<h1 style='color:red;'>HTML格式的：Hello World</h1>";
            helper.setText(content, true);
            mailSender.send(message);
            return "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 发送带附件的邮件
     *      发送带附件的邮件和普通邮件相比，其实就只是多了个传入附件的过程。不过使用的仍是MimeMessage：
     *
     * @return
     */
    @GetMapping("attachmentsMail")
    public String sendAttachmentsMail(){
        MimeMessage message;

        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(TO);
            helper.setSubject("这是一份带附件的邮件");
            helper.setText("详情请参照附件！");

            FileSystemResource resource = new FileSystemResource(
                    new File("C:\\Users\\Administrator\\Desktop\\周报-张威.xlsx")
            );
            helper.addAttachment(resource.getFilename(), resource);
            mailSender.send(message);
            return "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 发送带静态资源的邮件
     *
     * @return
     */
    @GetMapping("inlineMail")
    public String sendInlineMail(){
        MimeMessage message;

        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(TO);
            helper.setSubject("这是一封带静态资源的邮件");
            helper.setText("<html><body>刺激图片：<img src='cid:datavId'/></body></html>", true);
            // 传送附件(由于这是一个聚合工程，所以文件路径需要指定模块名，否则找不到图片)
            FileSystemResource resource = new FileSystemResource(
                    new File("springboot-mail/src/main/resources/static/images/datav.png")
            );
            // 注意：图片的cid要与这里设置的对应的上
            helper.addInline("datavId", resource);
            mailSender.send(message);
            return "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 使用模块发送邮件，例如发送短信验证码，格式都是一致，只有验证码值不同而已
     *
     * @return
     */
    @GetMapping("templateMail")
    public String sendTemplateMail(){
        MimeMessage message;

        try {
            message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(TO);
            helper.setSubject("这是一封模板邮件!");
            // 处理邮件模板
            Context context = new Context();
            context.setVariable("code", randomCode());
            // 此处的模板名称须与实际的HTML文件名称一致
            String templates = templateEngine.process("codeTemplates", context);
            helper.setText(templates, true);
            mailSender.send(message);
            return "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    /**
     * 随机生成六位数验证码
     *
     * @return
     */
    private String randomCode(){
        return String.valueOf(new Random().nextInt(899999) + 100000);
    }
}
