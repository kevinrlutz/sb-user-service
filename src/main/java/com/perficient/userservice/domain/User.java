package com.perficient.userservice.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class User {
    @Id
    private String id;

    private String firstName;
    private String lastName;

    private Integer age;
    private String gender;

    private String email;
    private String phoneNumber;
}
