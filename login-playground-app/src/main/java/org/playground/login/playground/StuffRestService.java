package org.playground.login.playground;

import org.playground.login.playground.error.ApplicatonError;
import org.playground.login.playground.pojo.StuffResponse;
import org.playground.login.playground.repository.UserRepository;
import org.playground.login.playground.repository.pojo.RegisteredUser;
import org.playground.login.playground.repository.pojo.Stuff;
import org.playground.login.playground.service.StuffService;
import org.playground.login.playground.service.TokenService;
import org.playground.login.playground.service.UserSession;
import org.playground.login.playground.service.UserSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest/")
public class StuffRestService {

    @Autowired
    StuffService stuffService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserSessionService userSessionService;

    @Autowired
    TokenService tokenService;

    public static final Logger LOGGER = LoggerFactory.getLogger(StuffRestService.class);

    @RequestMapping("stuff/{userId}/")
    @ResponseBody
    public StuffResponse getByStuffUser (@PathVariable("userId") int userId) throws ApplicatonError {
       // RegisteredUser registeredUser = tokenService.verifyToken()
        LOGGER.debug(SecurityContextHolder.getContext().getAuthentication().toString());
      List<Stuff> stuffs =  stuffService.getByUser(userId);
        return new StuffResponse(stuffs);
    }

    @PutMapping("stuff/{userId}/")
    @ResponseBody
    public ResponseEntity<Object> addStuff(@PathVariable("userId") int userId, @RequestBody Stuff stuff) throws ApplicatonError{
        RegisteredUser registeredUser = userRepository.getById(userId);
        stuffService.insert(registeredUser, stuff.getData());
        return ResponseEntity.ok().build();
    }


    @PostMapping("stuff/{userId}/")
    @ResponseBody
    public ResponseEntity<Object> upddateStuff(@PathVariable("userId") int userId, @RequestBody Stuff stuff) throws ApplicatonError{
        stuffService.update(stuff.getId(), stuff.getData());
        return ResponseEntity.ok().build();
    }
}
