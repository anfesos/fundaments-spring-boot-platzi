package com.studyplatzi.springboot.fundaments.configuration;

import com.studyplatzi.springboot.fundaments.casesuse.GetUser;
import com.studyplatzi.springboot.fundaments.casesuse.GetUserImplement;
import com.studyplatzi.springboot.fundaments.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CaseUseConfiguration {

    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImplement(userService);
    }
}
