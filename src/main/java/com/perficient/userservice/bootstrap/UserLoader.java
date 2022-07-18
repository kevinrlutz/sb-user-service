package com.perficient.userservice.bootstrap;

import com.perficient.userservice.domain.User;
import com.perficient.userservice.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public void run(String... args) throws Exception {
        if(userRepository.count() == 0 ) {
            loadUsers();
        }
    }

    private void loadUsers() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .age(30)
                .gender("Male")
                .email("johndoe@test.com")
                .phoneNumber("1234567890")
                .appointmentList(new ArrayList<>())
                .build();

        userRepository.save(user);
    }
}
