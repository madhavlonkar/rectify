package com.rentapp.utility;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Log4j {

    private final Logger logger = LoggerFactory.getLogger("RentifyLogger");

    @Pointcut("execution(* com.rentapp..*(..))")
    public void logAllMethods() {}

    @Before("logAllMethods()")
    public void logMethodEntry(JoinPoint joinPoint) {
        logger.info("Entering " + joinPoint.getSignature().getName() + " method");
    }

    @AfterReturning(pointcut = "logAllMethods()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        logger.info("Exiting " + joinPoint.getSignature().getName() + " method with result " + result);
    }
}
