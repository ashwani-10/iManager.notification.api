package com.example.iManagerNotification.service;

import com.example.iManagerNotification.kafkaConsumerDTO.UserRegistrationMessageDTO;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class UserRegistrationMailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    public void sendRegistrationMail(UserRegistrationMessageDTO messageDTO) {
        try {
            System.out.print("Generating meme message");
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            Context context = new Context();
            context.setVariable("OTP",messageDTO.getOtp());
            
            String htmlTemplate =templateEngine.process("otpVerification",context);
            mimeMessageHelper.setTo(messageDTO.getEmail());
            mimeMessageHelper.setSubject("EMAIL VERIFICATION");
            mimeMessageHelper.setText(htmlTemplate,true);

            javaMailSender.send(mimeMessage);
            System.out.print("message send");
        } catch (Exception e) {
            System.out.print("failure in sending mail");
        }
    }
}
