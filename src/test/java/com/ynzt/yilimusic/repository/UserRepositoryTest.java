package com.ynzt.yilimusic.repository;

import com.ynzt.yilimusic.entity.User;
import com.ynzt.yilimusic.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

//@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void funTest1() {
        User user = new User();
        user.setUsername("李麒麟");
        user.setNickname("云南昭通人");
        user.setPassword("admin1234");
        user.setGender(Gender.MALE);
        user.setEnabled(true);
        user.setLocked(true);
        user.setLastLoginIp("127.0.0.1");
        user.setLastLoginTime(new Date());

        User saveUser = userRepository.save(user);


        User user2 = userRepository.getByUsernameAndNickname("李麒麟","云南昭通人");

        System.out.println(user2.toString());
    }
}