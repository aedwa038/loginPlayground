package org.playground.login.playground.utils;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@Metrics
@Interceptor
@Component
public class MetricsInterceptor implements MethodInterceptor {
    public static boolean calledBefore = false;
    public static boolean calledAfter = false;
    public static final Logger LOGGER = LoggerFactory.getLogger(MetricsInterceptor.class);

    @AroundInvoke
    public Object auditMethod(InvocationContext ctx) throws Exception {
        LOGGER.info("Audited");
        calledBefore = true;
        Object result = ctx.proceed();
        calledAfter = true;
        return result;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        LOGGER.info("Audited");
        Object retVal = methodInvocation.proceed();
        return retVal;
    }
}
