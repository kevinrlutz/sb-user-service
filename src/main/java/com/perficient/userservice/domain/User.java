package com.perficient.userservice.domain;

import javax.annotation.processing.Generated;
import javax.persistence.Id;
import java.util.UUID;

public class User {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
