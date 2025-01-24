package com.pbs.edu.webapp.service;

import com.pbs.edu.webapp.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {

    private final RestTemplate restTemplate;
    @Value("${user.service.url}")
    private String userServiceUrl;


    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public User authenticate(String email, String password, HttpSession session) {
        String url = userServiceUrl + "/users/authenticate?email=" + email + "&password=" + password;
        ResponseEntity<User> response = restTemplate.postForEntity(url, null, User.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            User user = response.getBody();
            session.setAttribute("user", user);
            return user;
        }
        throw new RuntimeException("Invalid credentials");
    }
}
