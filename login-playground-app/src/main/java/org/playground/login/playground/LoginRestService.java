package org.playground.login.playground;

import org.playground.login.playground.error.ApplicatonError;
import org.playground.login.playground.pojo.*;
import org.playground.login.playground.service.TestService;
import org.playground.login.playground.service.UserLoginService;
import org.playground.login.playground.service.UserSession;
import org.playground.login.playground.service.UserSessionService;
import org.playground.login.playground.utils.CryptoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/rest/")
public class LoginRestService {


    @Autowired
    UserLoginService userLoginService;

    @Autowired
    TestService testService;

    @Autowired
    UserSessionService userSessionService;

    public static final Logger LOGGER = LoggerFactory.getLogger(LoginRestService.class);


    @PostMapping("register/")
    @ResponseBody
    public UserRegistrationResponse register(@RequestBody UserRegisterRequest userRegisterRequest) throws Exception {
        LOGGER.info("register");
        if(userRegisterRequest == null || userRegisterRequest.getEmail() == null || userRegisterRequest.getPassword() == null || userRegisterRequest.getUsername() == null){
            throw new ApplicatonError(ApplicatonError.ErrorCode.ILLEGAL_ARGUMENT,"Registration Error", "App Error");
        }
        //validate request
        return userLoginService.register(userRegisterRequest);
    }

    @PostMapping("login/")
    @ResponseBody
    public UserLoginResponse login(@RequestBody UserLoginRequest userLoginRequest,HttpServletResponse response) throws Exception {
        if(userLoginRequest == null || userLoginRequest.getUsername() == null || userLoginRequest.getPassword() == null || userLoginRequest.getUsername() == null){
            throw new ApplicatonError(ApplicatonError.ErrorCode.ILLEGAL_ARGUMENT,"Login Error", "App Error");
        }
        //validate response

        UserLoginResponse userLoginResponse = userLoginService.loginUser(userLoginRequest);
        UserSession userSession = UserSession.create(userLoginResponse.getUser());
        String sessionId = userSessionService.putSession(userSession);
        response.setHeader("SESSIONID", CryptoUtil.encode(sessionId));
        return userLoginResponse;
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
