package org.playground.login.playground.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;



public class LoginFilter implements Filter {

    public static final Logger LOGGER = LoggerFactory.getLogger(LoginFilter.class);


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        LOGGER.info("doFilter");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        LOGGER.info("Logging Request  {} : {}", req.getMethod(), req.getRequestURI());
        LOGGER.info("Logging connecton request   {}:{}", req.getRemoteAddr(), req.getRemotePort());
        chain.doFilter(request, response);
        LOGGER.info("Logging Response :{}", res.getContentType());
    }


}
