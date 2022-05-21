package com.studyplatzi.springboot.fundaments.service;

import com.studyplatzi.springboot.fundaments.entity.User;
import com.studyplatzi.springboot.fundaments.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {

    //Se crea el Log
    private final Log LOGGER = LogFactory.getLog(UserService.class);
    //SE inyecta la dependencia
    private UserRepository userRepository;
    //Se crea el constructor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveTransactional(List<User> users){
        users.stream()
                .peek(user -> LOGGER.info("Usuario insertado: " + user))
                .forEach(userRepository::save);
                //.forEach(user -> userRepository.save(user));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

    public void delete(Long id) {
        userRepository.delete(new User(id));
    }

    public User update(User newUser, Long id) {
        return userRepository.findById(id)
                .map(
                        user -> {
                            user.setEmail(newUser.getEmail());
                            user.setBirthDate(newUser.getBirthDate());
                            user.setName(newUser.getName());
                            return userRepository.save(user);
                        }
                ).get();

    }
}
