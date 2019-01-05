package org.playground.login.playground;

import org.playground.login.playground.error.ApplicatonError;
import org.playground.login.playground.pojo.*;
import org.playground.login.playground.service.*;
import org.playground.login.playground.utils.CryptoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/playground/")
public class LoginRestService {


    @Autowired
    UserLoginService userLoginService;

    @Autowired
    TestService testService;


    @Autowired
    TokenService tokenService;

    public static final Logger LOGGER = LoggerFactory.getLogger(LoginRestService.class);


    /**
     * @api {post} /playground/register Create a new User
     * @apiGroup User
     *
     * @apiParam {Object} request  Request Object
     * @apiParam {String} request.username  User name
     * @apiParam {String} request.password]  User Password
     * @apiParam {String} request.email  User email
     * @apiParamExample {json} Request-Example:
     * {
     *     	"username": "jd",
     *     	"password":"pdjklasjfw;of",
     *     	"email": "jd@gmail.com"
     * }
     * @apiHeader {String} Content-Type applicaton/json.
     * @apiHeaderExample {json} Header-Example:
     *  Content-Type:application/json
     *
     *
     * @apiSuccess {String} message Welcome Message.
     * @apiSuccess {Objct} user  Registered user .
     * @apiSuccess {Number} user.id  User Id.
     * @apiSuccess {String} user.username  username.
     * @apiSuccess {String} user.email  User email.
     * @apiSuccess {Date} user.registeredDate  Date user registered.
     * @apiSuccess {Date} user.lastLoginDate last login date of user.
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9...
     *     Content-Type:application/json;charset=UTF-8
     *     {
     *       "message": "Welcome John",
     *       "user": "{
     *           "id":1,
     *           "username": "jd",
     *           "email": "jogn@playgrounds.com",
     *           "registeredDate": "2019-01-04T04:17:33.522",
     *           "lastLoginDate": "2019-01-04T04:17:33.540271"
     *       }
     *     }
     *
     * @apiError UserNotFound The id of the User was not found.
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 404 Not Found
     *     {
     *      "code": 404
     *       "error": "UserNotFound"
     *     }
     */
    @PostMapping("register/")
    @ResponseBody
    public UserRegistrationResponse register(@RequestBody UserRegisterRequest userRegisterRequest,HttpServletResponse response) throws Exception {
        LOGGER.info("register");
        if(userRegisterRequest == null || userRegisterRequest.getEmail() == null || userRegisterRequest.getPassword() == null || userRegisterRequest.getUsername() == null){
            throw new ApplicatonError(ApplicatonError.ErrorCode.ILLEGAL_ARGUMENT,"Registration Error", "App Error");
        }
        //validate request

        UserRegistrationResponse userRegistrationResponse = userLoginService.register(userRegisterRequest);
        String token = tokenService.createToken(userRegistrationResponse.getUser());
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        return userRegistrationResponse;
    }


    /**
     * @api {get} playground/login/ Login User
     * @apiGroup User
     *
     * @apiParam {Object} request  Request Object
     * @apiParam {String} request.username  User name
     * @apiParam {String} request.password  User Password
     * @apiParamExample {json} Request-Example:
     * {
     *     	"username": "jd",
     *     	"password":"pdjklasjfw;of",
     * }
     * @apiHeader {String} Content-Type applicaton/json.
     * @apiHeaderExample {json} Header-Example:
     * Content-Type:application/json
     *
     * @apiSuccess {String} message Welcome Message.
     * @apiSuccess {Objct} user  Registered user .
     * @apiSuccess {Number} user.id  User Id.
     * @apiSuccess {String} user.username  username.
     * @apiSuccess {String} user.email  User email.
     * @apiSuccess {Date} user.registeredDate  Date user registered.
     * @apiSuccess {Date} user.lastLoginDate last login date of user.
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9...
     *     Content-Type:application/json;charset=UTF-8
     *     {
     *       "message": "Welcome John",
     *       "user": "{
     *           "id":1,
     *           "username": "jd",
     *           "email": "jogn@playgrounds.com",
     *           "registeredDate": "2019-01-04T04:17:33.522",
     *           "lastLoginDate": "2019-01-04T04:17:33.540271"
     *       }
     *     }
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 404 Not Found
     *     {
     *      "code": 404
     *       "error": "UserNotFound"
     *     }
     */
    @PostMapping("login/")
    @ResponseBody
    public UserLoginResponse login(@RequestBody UserLoginRequest userLoginRequest,HttpServletResponse response) throws Exception {
        if(userLoginRequest == null || userLoginRequest.getUsername() == null || userLoginRequest.getPassword() == null || userLoginRequest.getUsername() == null){
            throw new ApplicatonError(ApplicatonError.ErrorCode.ILLEGAL_ARGUMENT,"Login Error", "App Error");
        }
        //validate response
        UserLoginResponse userLoginResponse = userLoginService.loginUser(userLoginRequest);
        String token = tokenService.createToken(userLoginResponse.getUser());
        response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
        return userLoginResponse;
    }
    /**
     * @api {get} /playground/test/ Test Endpoint
     * @apiGroup User
     *
     * @apiSuccess {String} status Status Message.
     * @apiSuccess {Objct} user  Test user .
     * @apiSuccess {Number} user.id  User Id.
     * @apiSuccess {String} user.username  username.
     * @apiSuccess {String} user.email  User email.
     * @apiSuccess {Date} user.registeredDate  Date user registered.
     * @apiSuccess {Date} user.lastLoginDate last login date of user.
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     Content-Type:application/json;charset=UTF-8
     *     {
     *          "status": "OK",
     *          "testUser": {
     *          "id": 0,
     *          "username": "username",
     *          "email": "test@test.com",
     *          "registeredDate": "2019-01-04T03:49:00.568087",
     *          "lastLoginDate": "2019-01-04T03:49:00.568087"
     *         }
     *     }
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 404 Not Found
     *     {
     *      "code": 404
     *       "error": "UserNotFound"
     *     }
     */
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
