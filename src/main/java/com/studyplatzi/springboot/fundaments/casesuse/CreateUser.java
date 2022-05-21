package com.studyplatzi.springboot.fundaments.casesuse;

import com.studyplatzi.springboot.fundaments.entity.User;
import com.studyplatzi.springboot.fundaments.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {

    private UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    public User save(User newUser) {
        return userService.save(newUser);
    }
}
