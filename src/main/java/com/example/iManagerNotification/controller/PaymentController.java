package com.example.iManagerNotification.controller;

import com.example.iManagerNotification.kafkaConsumerDTO.PaymentMessageDTO;
import com.example.iManagerNotification.service.PaymentConfirmationMailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Autowired
    PaymentConfirmationMailService paymentMailService;
    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = {"user-registration"},groupId = "springboot-group-1")
    public void setPaymentMailService(ConsumerRecord<String, String> record) throws JsonProcessingException {
        String key = record.key();
        String value = record.value();
        if(!"payment-mail".equals(key)){
            System.out.println("key not matched in this topic");
            return;
        }
        System.out.println("key matched: "+key);

        PaymentMessageDTO message = objectMapper.readValue(value, PaymentMessageDTO.class);
        try {
            paymentMailService.sendRegistrationMail(message);
        }catch (Exception e){
            System.out.println("Payment mail nhi gaya");
        }

    }
}
