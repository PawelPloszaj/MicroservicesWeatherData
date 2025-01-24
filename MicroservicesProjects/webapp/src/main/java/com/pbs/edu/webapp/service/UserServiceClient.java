package com.pbs.edu.webapp.service;

import com.pbs.edu.webapp.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceClient {

    @Value("${user.service.url}")
    private String userServiceUrl;

    private final RestTemplate restTemplate;

    public UserServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void createUser(User user) {
        String url = userServiceUrl + "/users";
        restTemplate.postForObject(url, user, Void.class);
    }

    public List<User> getAllUsers() {
        String url = userServiceUrl + "/users";
        return List.of(restTemplate.getForObject(url, User[].class));
    }

    public User getUserById(Long id) {
        String url = userServiceUrl + "/users/" + id;
        return restTemplate.getForObject(url, User.class);
    }

    public void updateUser(User user) {
        String url = userServiceUrl + "/users/" + user.getId();
        restTemplate.put(url, user);
    }
}