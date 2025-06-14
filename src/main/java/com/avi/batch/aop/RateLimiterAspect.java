package com.avi.batch.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@Aspect
@Component
public class RateLimiterAspect {

    @Autowired(required = false)
    HttpServletRequest httpServletRequest;

    @Before("@annotation(rateLimiter)")
    public void rateLimiter(JoinPoint joinPoint, RateLimiter rateLimiter) throws Throwable {
        System.out.println("Inside RateLimiter");
    }

}