package com.example.iManagerNotification.service;

import com.example.iManagerNotification.kafkaConsumerDTO.PaymentMessageDTO;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class PaymentConfirmationMailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    public void sendRegistrationMail(PaymentMessageDTO payment) {
        try {
            System.out.print("Generating meme message");
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            Context context = new Context();
            context.setVariable("name",payment.getName());
            context.setVariable("userId",payment.getId());
            context.setVariable("subscription",payment.getSubscription());

            String htmlTemplate =templateEngine.process("paymentSuccess",context);
            mimeMessageHelper.setTo(payment.getEmail());
            mimeMessageHelper.setSubject("PAYMENT VERIFICATION");
            mimeMessageHelper.setText(htmlTemplate,true);

            javaMailSender.send(mimeMessage);
            System.out.print("message send");
        } catch (Exception e) {
            System.out.print("failure in sending mail");
        }
    }
}
