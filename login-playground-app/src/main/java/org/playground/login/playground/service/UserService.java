package org.playground.login.playground.service;

import org.playground.login.playground.error.ApplicatonError;
import org.playground.login.playground.repository.UserRepository;
import org.playground.login.playground.repository.pojo.RegisteredUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public RegisteredUser getById(int id) throws ApplicatonError{
        return userRepository.getById(id);
    }
}
