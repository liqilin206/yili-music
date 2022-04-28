package com.ynzt.yilimusic.repository;

import com.ynzt.yilimusic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    List<User> getByUsername(String username);

    User getByUsernameAndNickname(String username, String Nickname);

    Optional<User> findByUsername(String username);
}
