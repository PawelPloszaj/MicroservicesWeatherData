package com.pbs.edu.NotificationService.controller;

import com.pbs.edu.NotificationService.model.Notification;
import com.pbs.edu.NotificationService.repository.NotificationRepository;
import com.pbs.edu.NotificationService.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationRepository repository;

    @Autowired
    private EmailService emailService;

    @PostMapping
    public Notification sendNotification(@RequestBody Notification notification) {
        emailService.sendEmail(notification);
        notification.setSent(true);
        return repository.save(notification);
    }

    @GetMapping
    public List<Notification> getAllNotifications() {
        return repository.findAll();
    }
}