package org.playground.login.playground.service;

import org.playground.login.playground.repository.RegisteredUser;
import org.playground.login.playground.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    UserRepository userRepository;

    public boolean TestUser() {
        RegisteredUser registeredUser = userRepository.getRegisteredUser("username");

        return registeredUser != null;
    }
}
