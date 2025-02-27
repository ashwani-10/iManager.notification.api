package com.example.iManagerNotification.controller;

import com.example.iManagerNotification.kafkaConsumerDTO.UserRegistrationMessageDTO;
import com.example.iManagerNotification.service.UserRegistrationMailService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notification")
public class UserRegistrationNotification {
    @Autowired
    UserRegistrationMailService userRegistrationMailService;

    public void registrationMail(String message){
        System.out.println(message);
        int retries  = 3;

        while (retries--> 0) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                UserRegistrationMessageDTO user = objectMapper.readValue(message, UserRegistrationMessageDTO.class);
                userRegistrationMailService.sendRegistrationMail(user);
                System.out.println("Received User Registration: " + user.getEmail());
                break;
            } catch (Exception e) {
                if(retries == 0) {
                    throw new RuntimeException("Email sending failed after retries");
                }
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException ex){
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
