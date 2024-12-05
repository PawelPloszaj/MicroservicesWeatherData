package com.pbs.edu.NotificationService.repository;

import com.pbs.edu.NotificationService.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}