package com.perficient.userservice.repositories;

import com.mongodb.client.MongoClients;
import com.perficient.userservice.domain.User;
import com.perficient.userservice.services.UserService;
import com.perficient.userservice.web.mappers.UserMapper;
import com.perficient.userservice.web.model.UserDto;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.aspectj.lang.annotation.After;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ExtendWith(SpringExtension.class)
class UserRepositoryIT {

    @Autowired
    UserRepository userRepository;

    @Spy
    UserMapper userMapper = Mappers.getMapper(UserMapper.class);

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }


    @Test
    void getUserById() {
        UserDto userDto = getValidUserDto();

        UserDto savedUser = userMapper.toUserDto(userRepository.save(userMapper.toUser(userDto)));

        String testId = savedUser.getId();

        UserDto returnedUserDto = userMapper.toUserDto(userRepository.findById(testId).get());

        assertNotNull(returnedUserDto);
    }

    @Test
    void getUserById_no_match() {
        UserDto userDto = getValidUserDto();

        userRepository.save(userMapper.toUser(userDto));


        assertThrows(NoSuchElementException.class, () -> {
            userMapper.toUserDto(userRepository.findById(new ObjectId().toString()).get());
        });
    }

    @Test
    void findByLastNameIgnoreCase() {
        UserDto userDto = getValidUserDto();
        userRepository.save(userMapper.toUser(userDto));

        List<User> users = userRepository.findByLastNameIgnoreCase(userDto.getLastName());

        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    void findByLastNameIgnoreCase_no_match() {
        UserDto userDto = getValidUserDto();
        userRepository.save(userMapper.toUser(userDto));

        List<User> users = userRepository.findByLastNameIgnoreCase("not_a_match");

        assertThat(users.size()).isEqualTo(0);
    }

    @Test
    void findByEmailIgnoreCase() {
        UserDto userDto = getValidUserDto();
        userRepository.save(userMapper.toUser(userDto));

        List<User> users = userRepository.findByEmailIgnoreCase(userDto.getEmail());

        assertThat(users.size()).isEqualTo(1);
    }

    @Test
    void findByEmailIgnoreCase_no_match() {
        UserDto userDto = getValidUserDto();
        userRepository.save(userMapper.toUser(userDto));

        List<User> users = userRepository.findByEmailIgnoreCase("doesntexist@email.com");

        assertThat(users.size()).isEqualTo(0);
    }


    UserDto getValidUserDto() {
        return UserDto.builder()
                .id(new ObjectId().toString())
                .firstName("Joe")
                .lastName("Bloggs")
                .gender("Male")
                .age(18)
                .email("testuser@test.com")
                .phoneNumber("555-555-5555")
                .appointmentList(new ArrayList<>())
                .build();
    }
}