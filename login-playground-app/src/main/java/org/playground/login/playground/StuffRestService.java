package org.playground.login.playground;

import org.playground.login.playground.error.ApplicatonError;
import org.playground.login.playground.pojo.StuffResponse;
import org.playground.login.playground.repository.UserRepository;
import org.playground.login.playground.repository.pojo.RegisteredUser;
import org.playground.login.playground.repository.pojo.Stuff;
import org.playground.login.playground.service.*;
import org.playground.login.playground.utils.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/rest/")
public class StuffRestService {

    @Autowired
    StuffService stuffService;

    @Autowired
    UserService userService;

    public static final Logger LOGGER = LoggerFactory.getLogger(StuffRestService.class);

    private boolean isValidUser(RegisteredUser user) {
        try {
            if (userService.getById(user.getId()) != null) {
                return true;
            }
        } catch (ApplicatonError error) {
            LOGGER.error("Error checking if authenticated user: ", error);
        }
        return false;
    }
    /**
     * @api {get} /rest/stuff/:userId/ Get List of User data
     * @apiParam {Number} userId Users unique ID.
     * @apiHeader {String} User token.
     * @apiGroup Stuff
     *
     * @apiHeaderExample {text} Header-Example:
     * Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9
     *
     * @apiSuccess {Object} stuffResponse Response Object.
     * @apiSuccess {Object[]} stuffResponse.stuffList  List of Data Objects associated with the user
     * @apiSuccess {Number} stuffResonse.stuffList.user_id  User Id.
     * @apiSuccess {String} stuffResponse.stuffList.contentType  Content type of the response Data.
     * @apiSuccess {Number} stuffResponse.stuffList.data  Data.
     *
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     Content-Type:application/json;charset=UTF-8
     *     {
     *          "stuffList": [
     *              {
     *                  "user_id": 1,
     *                  "contentType": "application/json",
     *                  "id": 5,
     *                  "data": "{\"data\":\"{}\"}"
     *              }
     *          ]
     *     }
     *
     * @apiError TODO: todo
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 404 Not Found
     *     {
     *      "code": 404
     *       "error": "StuffNotFond"
     *     }
     */
    @RequestMapping("stuff/{userId}/")
    @ResponseBody
    public StuffResponse getByStuffUser (@PathVariable("userId") int userId) throws ApplicatonError {
       RegisteredUser user = RequestContext.getRegisteredUser();
       if(userId  != user.getId() || !isValidUser(user)) {
           throw new ApplicatonError(ApplicatonError.ErrorCode.AUTHORIZATION_ERROR, "Authorization Error","User Token Error");
       }

      List<Stuff> stuffs =  stuffService.getByUser(userId);
        return new StuffResponse(stuffs);
    }
    /**
     * @api {get} /rest/stuff/:userId/:stuffId Get user data
     * @apiParam {Number} userId Users unique ID.
     * @apiParam {Number} stuffId stuff unique ID.
     * @apiHeader {String} User token.
     * @apiGroup Stuff
     *
     * @apiHeaderExample {text} Header-Example:
     * Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9
     *
     * @apiSuccess {Object} Generic Response Object.
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     Content-Type:application/json
     *     {
     *         "_id": "5c2ffe3bb1d1aa3ad3fcd4ee",
     *         "index": 0,
     *          "isActive": true,
     *          "balance": "$3,672.08",
     *          "name": {
     *              "first": "Rosalinda",
     *              "last": "Rocha"
     *          }
     *     }
     *
     * @apiError TODO: todo
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 404 Not Found
     *     {
     *      "code": 404
     *       "error": "StuffNotFond"
     *     }
     */
    @RequestMapping("stuff/{userId}/{stuffId}")
    public ResponseEntity<Object> getStuff (@PathVariable("userId") int userId, @PathVariable("stuffId") int stuffId, HttpServletResponse res) throws ApplicatonError {
        RegisteredUser user = RequestContext.getRegisteredUser();
        if(userId  != user.getId() || !isValidUser(user)) {
            throw new ApplicatonError(ApplicatonError.ErrorCode.AUTHORIZATION_ERROR, "Authorization Error","User Token Error");
        }
        Stuff stuff =  stuffService.getById(stuffId, userId);
        if(stuff == null) {
            throw new ApplicatonError(ApplicatonError.ErrorCode.AUTHORIZATION_ERROR, "Authorization Error","User Token Error");
        }
        return ResponseEntity.ok().contentType(MediaType.parseMediaType(stuff.getContentType())).body(stuff.getData());
    }
    /**
     * @api {put} /rest/stuff/:userId/  Add new User data
     * @apiParam {Number} userId Users unique ID.
     * @apiHeader {String} User token.
     * @apiGroup Stuff
     *
     * @apiHeaderExample {text} Header-Example:
     * Authorization:Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9
     *
     * @apiSuccess {Object} Generic Response Object.
     * @apiSuccessExample Success-Response:
     *     HTTP/1.1 200 OK
     *     Content-Type:application/json
     *     id:5
     *     {
     *         "_id": "5c2ffe3bb1d1aa3ad3fcd4ee",
     *         "index": 0,
     *          "isActive": true,
     *          "balance": "$3,672.08",
     *          "name": {
     *              "first": "Rosalinda",
     *              "last": "Rocha"
     *          }
     *     }
     *
     * @apiError TODO: todo
     *
     * @apiErrorExample Error-Response:
     *     HTTP/1.1 404 Not Found
     *     {
     *      "code": 404
     *       "error": "StuffNotFond"
     *     }
     */
    @PutMapping("stuff/{userId}/")
    @ResponseBody
    public ResponseEntity<Object> addStuff(@PathVariable("userId") int userId, @RequestBody String stuff, HttpServletRequest req, HttpServletResponse resp) throws ApplicatonError{
        RegisteredUser user = RequestContext.getRegisteredUser();
        if(userId  != user.getId() || !isValidUser(user)) {
            throw new ApplicatonError(ApplicatonError.ErrorCode.AUTHORIZATION_ERROR, "Authorization Error","User Token Error");
        }
        String contentType = req.getContentType();
        if(contentType == null || "".equals(contentType)) {
            contentType = "plain/text";
        }

        Integer id = stuffService.insert(user, stuff, contentType);
        resp.setHeader("id", id.toString());
        return ResponseEntity.ok().build();
    }


    @PostMapping("stuff/{userId}/")
    @ResponseBody
    public ResponseEntity<Object> upddateStuff(@PathVariable("userId") int userId, @RequestBody Stuff stuff) throws ApplicatonError{
        RegisteredUser user = RequestContext.getRegisteredUser();
        if(userId  != user.getId() || !isValidUser(user)) {
            throw new ApplicatonError(ApplicatonError.ErrorCode.AUTHORIZATION_ERROR, "Authorization Error","User Token Error");
        }
        stuffService.update(stuff.getId(), stuff.getData());
        return ResponseEntity.ok().build();
    }
}
