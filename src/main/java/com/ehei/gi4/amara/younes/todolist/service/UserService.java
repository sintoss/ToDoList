package com.ehei.gi4.amara.younes.todolist.service;

import com.ehei.gi4.amara.younes.todolist.model.User;
import com.ehei.gi4.amara.younes.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(String login, String password, String email) {
        if (login == null || password == null) {
            return null;
        } else {
            if (userRepository.findFirstByLogin(login).isPresent()) {
                System.out.println("duplicated login");
                return null;
            }
            User userModel = new User();
            userModel.setLogin(login);
            userModel.setPassword(password);
            userModel.setEmail(email);
            return userRepository.save(userModel);


        }
    }

    public User authenticate(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password).orElse(null);

    }
}
