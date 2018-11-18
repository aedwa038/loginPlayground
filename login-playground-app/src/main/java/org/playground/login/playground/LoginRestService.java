package org.playground.login.playground;

import org.playground.login.playground.pojo.*;
import org.playground.login.playground.service.TestService;
import org.playground.login.playground.service.UserLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/")
public class LoginRestService {


    @Autowired
    UserLoginService userLoginService;

    @Autowired
    TestService testService;

    public static final Logger LOGGER = LoggerFactory.getLogger(LoginRestService.class);


    @PostMapping("register/")
    @ResponseBody
    public UserRegistrationResponse register(@RequestBody UserRegisterRequest userRegisterRequest) throws Exception {
        LOGGER.info("register");
        if(userRegisterRequest == null || userRegisterRequest.getEmail() == null || userRegisterRequest.getPassword() == null || userRegisterRequest.getUsername() == null){
            throw new ApplicatonError("Registration Error", "App Error");
        }
        //validate request
        return userLoginService.register(userRegisterRequest);
    }

    @PostMapping("login/")
    @ResponseBody
    public UserLoginResponse login(@RequestBody UserLoginRequest userLoginRequest) throws Exception {
        if(userLoginRequest == null || userLoginRequest.getUsername() == null || userLoginRequest.getPassword() == null || userLoginRequest.getUsername() == null){
            throw new ApplicatonError("Login Error", "App Error");
        }
        //validate response
        return userLoginService.loginUser(userLoginRequest);
    }

    @RequestMapping("test/")
    @ResponseBody
    public HealthResponse test () {
        LOGGER.info("test");
        try {
            return new HealthResponse(testService.TestUser());
        } catch (Exception ex) {
            LOGGER.warn("Health Check Failure");
            LOGGER.error("Health Check Error", ex);
        }
        return new HealthResponse("NOT OK");

    }


}
