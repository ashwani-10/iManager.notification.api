package com.example.iManagerNotification.controller;

import com.example.iManagerNotification.service.InviteUserMailService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/notification")
public class UserInviteMailController {
    @Autowired
    InviteUserMailService inviteUser;


    @KafkaListener(topics = {"user-registration"},groupId = "invite-group")
    public void inviteMail(ConsumerRecord<String, String> record){
        String key = record.key();
        String value = record.value();
        System.out.println(key+" "+key.length());
        System.out.println(value);

        if(!key.equals("invite-mail")){
            System.out.println("invite-mail".length());
            System.out.println("key not matched in "+"invite-mail");
            return;
        }
        System.out.println("key matched: "+key);
        String [] details = value.split(":");
        String userEmail = details[0].replace("\"", "");
        String orgName = details[1].replace("\"", "");
        try {
            inviteUser.sendInviteMail(userEmail,orgName);
            System.out.println("User Invited Successfully");
        }catch (Exception e){
            System.out.println("failed inviting user");
        }
    }


    @GetMapping("/docker")
    public String dockerTest(){
        return "Docker connected with notification service";
    }
}
