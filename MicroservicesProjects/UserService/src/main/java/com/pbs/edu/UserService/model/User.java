package com.pbs.edu.UserService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private boolean rainNotification;
    private boolean snowNotification;
    private boolean windNotification;
    private boolean highTempNotification;
    private boolean lowTempNotification;

    @NotBlank
    private String country;

    @NotBlank
    private String city;
}
