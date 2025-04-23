package com.example.iManagerNotification.service;

import com.example.iManagerNotification.kafkaConsumerDTO.TaskAssignedMessageDTO;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class TaskAssigneeMailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    TemplateEngine templateEngine;

    public void sendTaskAssigneeMail(TaskAssignedMessageDTO messageDTO) {
        try {
            System.out.print("Generating meme message");
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);

            Context context = new Context();
            context.setVariable("assigneeName",messageDTO.getAssignedName());
            context.setVariable("taskTitle",messageDTO.getTaskTitle());
            context.setVariable("priority",messageDTO.getPriority());
            context.setVariable("dueDate",messageDTO.getDueDate());

            String htmlTemplate =templateEngine.process("taskAssigned",context);
            mimeMessageHelper.setTo(messageDTO.getAssignedMail());
            mimeMessageHelper.setSubject("TASK ASSIGNED MAIL");
            mimeMessageHelper.setText(htmlTemplate,true);

            javaMailSender.send(mimeMessage);
            System.out.print("message send");
        } catch (Exception e) {
            System.out.print("failure in sending mail");
        }
    }
}
