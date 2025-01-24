package com.pbs.edu.webapp.service;

import com.pbs.edu.webapp.model.NotificationRequest;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class NotificationServiceClient {
    @Value("${notification.service.url}")
    private String notificationServiceUrl;

    private final RestTemplate restTemplate;

    public NotificationServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendNotification(String email, String subject, String message, LocalDateTime timestamp, boolean sent) {
        String url = notificationServiceUrl + "/notifications";
        NotificationRequest request = new NotificationRequest(email, subject, message, timestamp, sent);
        restTemplate.postForObject(url, request, Void.class);
    }

    public List<NotificationRequest> getNotificationsForUser(String email) {
        String url = notificationServiceUrl + "/notifications/by-email?email=" + email;
        NotificationRequest[] notifications = restTemplate.getForObject(url, NotificationRequest[].class);
        return Arrays.asList(notifications);
    }
}
