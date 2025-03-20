package com.example.iManagerNotification.kafkaConsumerDTO;

import com.example.iManagerNotification.Subscription;

import java.util.List;
import java.util.UUID;

public class PaymentMessageDTO {
    private UUID id;

    private String name;

    private String email;

    private String password;

    Subscription subscription;

    private String amount;

    public PaymentMessageDTO(UUID id, String name, String email, String password, Subscription subscription, String amount) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.subscription = subscription;
        this.amount = amount;
    }

    public PaymentMessageDTO() {
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }
}
