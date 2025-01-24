package com.pbs.edu.webapp.controller;

import com.pbs.edu.webapp.model.User;
import com.pbs.edu.webapp.service.UserServiceClient;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserServiceClient userService;

    public UserController(UserServiceClient userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user) {
        userService.createUser(user);
        return "redirect:/login";
    }

    @GetMapping("/settings")
    public String showSettingsForm(Model model, HttpSession session) {
        User authenticatedUser = (User) session.getAttribute("user");
        if (authenticatedUser == null) {
            return "redirect:/login";
        }
        User user = userService.getUserById(authenticatedUser.getId());
        model.addAttribute("user", user);
        return "settings";
    }

    @PostMapping("/settings")
    public String updateSettings(@ModelAttribute User user, HttpSession session) {
        User authenticatedUser = (User) session.getAttribute("user");
        if (authenticatedUser == null || !authenticatedUser.getId().equals(user.getId())) {
            return "redirect:/login";
        }
        userService.updateUser(user);
        session.setAttribute("user", userService.getUserById(user.getId()));
        return "redirect:/settings";
    }

}
