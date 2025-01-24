package com.pbs.edu.UserService.repository;

import com.pbs.edu.UserService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
