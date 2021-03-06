package org.playground.login.playground.service;

import org.playground.login.playground.error.ApplicatonError;
import org.playground.login.playground.pojo.UserLoginResponse;
import org.playground.login.playground.pojo.UserLoginRequest;
import org.playground.login.playground.pojo.UserRegisterRequest;
import org.playground.login.playground.pojo.UserRegistrationResponse;
import org.playground.login.playground.repository.pojo.RegisteredUser;
import org.playground.login.playground.repository.UserRepository;
import org.playground.login.playground.utils.CryptoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Service
public class UserLoginService {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserLoginService.class);

    @Autowired
    UserRepository userRepository;

    @Transactional(transactionManager = "txManager")
    public UserLoginResponse loginUser(UserLoginRequest request) throws Exception{
        String password = userRepository.getUserPassword(request.getUsername());
        if(password == null || "".equals(password)) {
            throw new ApplicatonError(ApplicatonError.ErrorCode.AUTHENTICATION_ERROR,"Authentication Error","Authentication Error");
        }
        if(!CryptoUtil.checkPassword(request.getPassword(), password)) {
           throw new ApplicatonError(ApplicatonError.ErrorCode.AUTHENTICATION_ERROR,"Authentication Error","Authentication Error");
        }
        RegisteredUser user = userRepository.getRegisteredUser(request.getUsername());
        userRepository.updateLastlogin(request.getUsername());

        return new UserLoginResponse(user);
    }

    @Transactional(transactionManager = "txManager")
    public UserRegistrationResponse register(UserRegisterRequest request) throws Exception {
        LOGGER.info("register");
        CompletableFuture<Boolean> emails = userRepository.checkByEmail(request.getEmail());
        CompletableFuture<Boolean> usernames = userRepository.checkByUsername(request.getUsername());
        CompletableFuture.allOf(emails, usernames).join();

        if(!emails.get() || !usernames.get()) {
            LOGGER.error("Username or email exists");
            throw new ApplicatonError(ApplicatonError.ErrorCode.REGISRATION_ERROR,"email and username check", "value already exisits");
        }

        RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setUsername(request.getUsername());
        registeredUser.setEmail(request.getEmail());
        registeredUser.setPassword(CryptoUtil.hash(request.getPassword()));
        registeredUser.setRegisteredDate(LocalDateTime.now());
        userRepository.insertUser(registeredUser);

        RegisteredUser user = userRepository.getRegisteredUser(request.getUsername());
        return new UserRegistrationResponse(user);
    }
}
