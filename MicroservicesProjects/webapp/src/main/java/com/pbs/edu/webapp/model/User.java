package com.pbs.edu.webapp.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class User {
    private Long id;

    private String email;

    private String password;

    private boolean rainNotification;
    private boolean snowNotification;
    private boolean windNotification;
    private boolean highTempNotification;
    private boolean lowTempNotification;

    private String country;

    private String city;

}
