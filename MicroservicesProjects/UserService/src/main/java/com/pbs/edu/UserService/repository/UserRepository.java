package com.pbs.edu.UserService.repository;

import com.pbs.edu.UserService.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
