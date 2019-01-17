package org.playground.login.playground.api;

import org.playground.login.playground.error.ApplicatonError;
import org.playground.login.playground.pojo.StuffResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface StuffRestServiceApi {

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
    public StuffResponse getByStuffUser (@PathVariable("userId") int userId) throws ApplicatonError;

    /**
     * @api {get} /rest/stuff/:userId/:stuffId/ Get user data
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
    @RequestMapping("stuff/{userId}/{stuffId/}")
    ResponseEntity<Object> getStuff (@PathVariable("userId") int userId, @PathVariable("stuffId") int stuffId, HttpServletResponse res) throws ApplicatonError;

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
    ResponseEntity<Object> addStuff(@PathVariable("userId") int userId, @RequestBody String stuff, HttpServletRequest req, HttpServletResponse resp) throws ApplicatonError;
}
