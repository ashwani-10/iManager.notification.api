package com.example.iManagerNotification.controller;

import com.example.iManagerNotification.kafkaConsumerDTO.PaymentMessageDTO;
import com.example.iManagerNotification.kafkaConsumerDTO.TaskAssignedMessageDTO;
import com.example.iManagerNotification.service.TaskAssigneeMailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaskAssigneeMailController {
    @Autowired
    TaskAssigneeMailService taskAssigneeMailService;
    @Autowired
    ObjectMapper objectMapper;

    @KafkaListener(topics = {"task-events"},groupId = "taskAssignee-group")
    public void setTaskAssigneeMail(ConsumerRecord<String, String> record) throws JsonProcessingException {
        String key = record.key();
        String value = record.value();
        if(!key.equals("assignee-mail")){
            System.out.println("key not matched in "+"assignee-mail");
            return;
        }
        System.out.println("key matched: "+key);

        TaskAssignedMessageDTO message = objectMapper.readValue(value, TaskAssignedMessageDTO.class);
        try {
            taskAssigneeMailService.sendTaskAssigneeMail(message);
        }catch (Exception e){
            System.out.println("assignee mail nhi gaya");
        }
    }
}
