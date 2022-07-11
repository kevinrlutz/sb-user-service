package com.perficient.userservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("users")
public class User {
    @Id
    private String userId;

    @Version
    private Long version;

    private String firstName;
    private String lastName;

    private Integer age;
    private String gender;

    private String email;
    private String phoneNumber;
}
