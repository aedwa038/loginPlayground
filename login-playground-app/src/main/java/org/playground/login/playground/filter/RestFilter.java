package org.playground.login.playground.filter;

import org.playground.login.playground.error.ApplicatonError;
import org.playground.login.playground.error.FilterError;
import org.playground.login.playground.repository.pojo.RegisteredUser;
import org.playground.login.playground.service.UserSession;
import org.playground.login.playground.service.UserSessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;


public class RestFilter implements Filter {

    public static final Logger LOGGER = LoggerFactory.getLogger(RestFilter.class);
    @Autowired
    UserSessionService userSessionService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("doFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        LOGGER.info(
                "Logging Request  {} : {}", req.getMethod(),
                req.getRequestURI());
        Optional<String> sessionId = getSessionId(req);
        sessionId.ifPresent(s -> {
                    System.out.println(s);
                    Optional<UserSession> user = userSessionService.getSession(s);
                    if(!user.isPresent()) {
                        LOGGER.error("No user found");
                        //throw new FilterError(500, "Internal Session Error");
                    }

            }
        );
        chain.doFilter(request, response);

        LOGGER.info(
                "Logging Response :{}",
                res.getContentType());

    }

    private Optional<String> getSessionId(HttpServletRequest request) {
        Optional<String> optional = Optional.ofNullable(request.getHeader("SESSIONID"));
        return optional;
    }
}
