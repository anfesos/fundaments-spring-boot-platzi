package com.studyplatzi.springboot.fundaments.casesuse;

import com.studyplatzi.springboot.fundaments.entity.User;
import com.studyplatzi.springboot.fundaments.service.UserService;

import java.util.List;

public class GetUserImplement implements GetUser{

    //Se inyecta la dependencia a partir del constructor
    private UserService userService;

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}
