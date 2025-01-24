package com.pbs.edu.webapp.model;

import java.time.LocalDateTime;

public class NotificationRequest {

    public String email;
    public String subject;
    public LocalDateTime timestamp;
    public String message;
    public boolean sent;

    public NotificationRequest(String email, String subject, String message, LocalDateTime timestamp, boolean sent) {
        this.email = email;
        this.subject = subject;
        this.timestamp = timestamp;
        this.message = message;
        this.sent = sent;
    }

}