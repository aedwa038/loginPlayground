package org.playground.login.playground.utils;

import org.playground.login.playground.error.ApplicatonError;
import org.playground.login.playground.repository.pojo.RegisteredUser;
import org.playground.login.playground.service.TokenService;
import org.springframework.security.core.context.SecurityContextHolder;

public class RequestContext {

    private RequestContext() {
    }

    public static RegisteredUser getRegisteredUser() throws ApplicatonError {
        String registerdUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if(registerdUser == null) {
            throw new ApplicatonError(ApplicatonError.ErrorCode.AUTHENTICATION_ERROR, "", "");
        }
        RegisteredUser user = TokenService.fromClaims(registerdUser).get();
        if(user == null) {
            throw new ApplicatonError(ApplicatonError.ErrorCode.AUTHENTICATION_ERROR, "", "");
        }
       return  user;
    }
}
