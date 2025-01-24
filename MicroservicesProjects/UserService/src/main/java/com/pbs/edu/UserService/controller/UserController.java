package com.pbs.edu.UserService.controller;

import com.pbs.edu.UserService.model.User;
import com.pbs.edu.UserService.repository.UserRepository;
import com.pbs.edu.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        User existingUser = userService.getUserById(id);
        if (existingUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        existingUser.setCity(updatedUser.getCity());
        existingUser.setCountry(updatedUser.getCountry());
        existingUser.setRainNotification(updatedUser.isRainNotification());
        existingUser.setSnowNotification(updatedUser.isSnowNotification());
        existingUser.setWindNotification(updatedUser.isWindNotification());
        existingUser.setHighTempNotification(updatedUser.isHighTempNotification());
        existingUser.setLowTempNotification(updatedUser.isLowTempNotification());

        userService.updateUser(existingUser);
        return ResponseEntity.ok(existingUser);
    }

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<User> authenticateUser(@RequestParam String email, @RequestParam String password) {
        User user = userRepository.findByEmail(email);
        if(user != null && userService.checkPass(password, user.getPassword())) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
