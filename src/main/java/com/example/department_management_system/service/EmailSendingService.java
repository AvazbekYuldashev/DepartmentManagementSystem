package com.example.department_management_system.service;


import com.example.department_management_system.enums.AppLangulage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import com.example.department_management_system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EmailSendingService {
    @Value("${spring.mail.username}")
    private String fromAccaunt;
    @Value("${server.domain}")
    private String serverDomain;

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private ResourceBoundleService boundleService;

    public void sendRegistrationEmail(String email, Integer profileId, AppLangulage lang) {
        String subject = "Complete Registration";
        String body = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "    <style>\n" +
                "        a {\n" +
                "            padding: 10px 30px;\n" +
                "            display: inline-block;\n" +
                "        }\n" +
                "        .buttin-link {\n" +
                "            text-decoration: none;\n" +
                "            color: white;\n" +
                "            background-color: indianred;\n" +
                "        }\n" +
                "        .buttin-link:hover {\n" +
                "            background-color: #dd4444;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h1>Complete Registration</h1>\n" +
                "<p>\n" +
                "    %s\n" +
                "    <a class=\"buttin-link\"\n" +
                "\n" +
                "        href=\"%s/auth/registration/verification/%s/%s\" target=\"_blank\">Click there</a>\n" +
                "</p>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        body = String.format(body, boundleService.getMessage("send.text", lang), serverDomain, JwtUtil.encodeVer(profileId), lang.name());
        System.out.println(JwtUtil.encodeVer(profileId));
        sendMimeEmail(email, subject, body);
    }


    public void sendMimeEmail(String email, String subject, String body) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            mimeMessage.setFrom(fromAccaunt);
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body, true);

            CompletableFuture.runAsync(() -> {
                mailSender.send(mimeMessage);
            });

            CompletableFuture.runAsync(() -> {
            });

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendSimpleEmail(String email, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromAccaunt);
        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}