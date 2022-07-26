package com.perficient.userservice.domain;

import com.perficient.userservice.web.model.ApptDto;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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

    @Indexed(unique = true)
    private String email;

    private String phoneNumber;

    private List<ApptDto> appointmentList;
}
