package org.playground.login.playground.service;

import org.playground.login.playground.ApplicatonError;
import org.playground.login.playground.pojo.UserLoginResponse;
import org.playground.login.playground.pojo.UserLoginRequest;
import org.playground.login.playground.pojo.UserRegisterRequest;
import org.playground.login.playground.pojo.UserRegistrationResponse;
import org.playground.login.playground.repository.pojo.RegisteredUser;
import org.playground.login.playground.repository.UserRepository;
import org.playground.login.playground.utils.CryptoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserLoginService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public UserLoginResponse loginUser(UserLoginRequest request) throws Exception{
        String password = userRepository.getUserPassword(request.getUsername());

        if(!CryptoUtil.checkPassword(request.getPassword(), password)) {
           throw new ApplicatonError("", "UserLogin Error");
        }
        RegisteredUser user = userRepository.getRegisteredUser(request.getUsername());
        userRepository.updateLastlogin(request.getUsername());

        return new UserLoginResponse("");
    }

    @Transactional
    public UserRegistrationResponse register(UserRegisterRequest request) throws Exception {
        if(!userRepository.checkByEmail(request.getEmail()) || !userRepository.checkByUsername(request.getUsername())) {
            throw new ApplicatonError("email and username check", "value already exisits");
        }

        RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setUsername(request.getUsername());
        registeredUser.setEmail(request.getPassword());
        registeredUser.setPassword(CryptoUtil.hash(request.getPassword()));

        if(!userRepository.insertUser(registeredUser)) {
            throw new ApplicatonError("Error insert", "Error inserting user");
        }
        RegisteredUser user = userRepository.getRegisteredUser(request.getUsername());


        return new UserRegistrationResponse(user);
    }
}
