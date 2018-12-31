package org.playground.login.playground.service;

import org.playground.login.playground.error.ApplicatonError;
import org.playground.login.playground.repository.pojo.RegisteredUser;
import org.playground.login.playground.repository.UserRepository;
import org.playground.login.playground.utils.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    UserRepository userRepository;

    @Metrics
    public RegisteredUser TestUser() throws ApplicatonError {
        RegisteredUser registeredUser = userRepository.getRegisteredUser("username");
        return registeredUser;
    }
}
