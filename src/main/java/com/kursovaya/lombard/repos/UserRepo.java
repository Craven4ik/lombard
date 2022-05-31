package com.kursovaya.lombard.repos;

import com.kursovaya.lombard.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
