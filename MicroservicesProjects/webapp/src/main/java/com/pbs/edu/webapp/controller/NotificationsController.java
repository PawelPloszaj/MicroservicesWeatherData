package com.pbs.edu.webapp.controller;

import com.pbs.edu.webapp.model.NotificationRequest;
import com.pbs.edu.webapp.model.User;
import com.pbs.edu.webapp.service.NotificationServiceClient;
import com.pbs.edu.webapp.service.UserServiceClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class NotificationsController {

    private final NotificationServiceClient notificationServiceClient;
    private final UserServiceClient userService;

    public NotificationsController(NotificationServiceClient notificationsServiceClient, UserServiceClient userService) {
        this.notificationServiceClient = notificationsServiceClient;
        this.userService = userService;
    }

    @GetMapping("/notifications")
    public String getNotifications(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        String userEmail = user.getEmail();
        List<NotificationRequest> notifications = notificationServiceClient.getNotificationsForUser(userEmail);
        model.addAttribute("notifications", notifications);
        model.addAttribute("user", session.getAttribute("user"));
        return "notifications";
    }

}
