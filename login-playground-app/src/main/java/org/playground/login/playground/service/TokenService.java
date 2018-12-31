package org.playground.login.playground.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import org.playground.login.playground.pojo.SecurityConstants;
import org.playground.login.playground.repository.pojo.RegisteredUser;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TokenService {

    private static final JWTVerifier VERIFIER;

    static {
        VERIFIER = JWT.require(SecurityConstants.ALGORITHM)
                .withIssuer(SecurityConstants.ISSUER)
                .build();
    }


    public String createToken(RegisteredUser user) {
        Gson gson = new Gson();

        String token = JWT.create()
                .withIssuer(SecurityConstants.ISSUER)
                .withClaim("user", gson.toJson(user))
                .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                .sign(SecurityConstants.ALGORITHM);
        return token;
    }

    public Optional<String> verifyToken(String token) {
        Gson gson = new Gson();
        Optional<String> toReturn = Optional.ofNullable(null);
        DecodedJWT  decodedJWT= verify(token);
        if (decodedJWT != null || decodedJWT.getClaim("user").isNull()) {
           String userString =  decodedJWT.getClaim("user").asString();
           RegisteredUser user = fromClaims(token).get();
           if(user != null) {
               toReturn = Optional.of(userString);
           }
        }

        return toReturn;
    }

    public Optional<RegisteredUser> fromClaims(String claim) {
        Gson gson = new Gson();
        Optional<RegisteredUser> registeredUserOptional = Optional.ofNullable(null);
        RegisteredUser user = gson.fromJson(claim, RegisteredUser.class);
        if(user != null) {
            registeredUserOptional = Optional.of(user);
        }

        return registeredUserOptional;
    }

    private DecodedJWT verify(String token) {
        try {
            return VERIFIER.verify(token);
        } catch (JWTVerificationException e) {
            return null;
        }
    }



}
