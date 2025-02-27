package com.example.iManagerNotification.kafkaConsumerDTO;

public class UserRegistrationMessageDTO {
    String name;
    String email;

    String otp;

    public UserRegistrationMessageDTO(String name, String email, String otp) {
        this.name = name;
        this.email = email;
        this.otp = otp;
    }

    public UserRegistrationMessageDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
