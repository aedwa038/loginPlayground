package org.playground.login.playground.api;

import org.playground.login.playground.pojo.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

public interface LoginRestServiceApi {

    /**
     * @api {post} /playground/register/ Create a new User
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
    UserRegistrationResponse register(@RequestBody UserRegisterRequest userRegisterRequest, HttpServletResponse response) throws Exception ;

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
    UserLoginResponse login(@RequestBody UserLoginRequest userLoginRequest, HttpServletResponse response) throws Exception;

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
    HealthResponse test ();
}
