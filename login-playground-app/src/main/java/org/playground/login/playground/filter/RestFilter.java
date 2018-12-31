package org.playground.login.playground.filter;

import org.playground.login.playground.error.ApplicatonError;
import org.playground.login.playground.error.FilterError;
import org.playground.login.playground.pojo.SecurityConstants;
import org.playground.login.playground.repository.pojo.RegisteredUser;
import org.playground.login.playground.service.TokenService;
import org.playground.login.playground.service.UserSession;
import org.playground.login.playground.service.UserSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;


public class RestFilter implements Filter {

    public static final Logger LOGGER = LoggerFactory.getLogger(RestFilter.class);

    @Autowired
    TokenService tokenService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("doFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        LOGGER.info(
                "Logging Request  {} : {}", req.getMethod(),
                req.getRequestURI());
        UsernamePasswordAuthenticationToken authentication = authenticate(req);
        if(authentication == null) {
            throw new ServletException("Missing or invalid Authorization header");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

        LOGGER.info(
                "Logging Response :{}",
                res.getContentType());

    }

    UsernamePasswordAuthenticationToken authenticate(HttpServletRequest request) throws ServletException {
        String token =  request.getHeader(SecurityConstants.HEADER_STRING);
        if(token == null) {
            throw new ServletException("Missing or invalid Authorization header");
        }

        Optional<String> userOptional = tokenService.verifyToken(token);

        if(!userOptional.isPresent()){
            throw new ServletException("Missing or invalid Authorization header");
        }
        String registeredUserString = userOptional.get();
        if (registeredUserString != null) {
            return new UsernamePasswordAuthenticationToken(registeredUserString, null, new ArrayList<>());
        }
        return null;
    }

}
