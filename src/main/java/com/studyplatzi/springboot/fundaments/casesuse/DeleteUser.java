package com.studyplatzi.springboot.fundaments.casesuse;

import com.studyplatzi.springboot.fundaments.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DeleteUser {

    private UserService userService;

    public DeleteUser(UserService userService) {
        this.userService = userService;
    }

    public void delete(Long id) {
        userService.delete(id);
    }
}
