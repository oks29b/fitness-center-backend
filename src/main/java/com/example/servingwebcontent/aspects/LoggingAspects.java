package com.example.servingwebcontent.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspects {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Pointcut that matches all repositories, services.
     */

    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)")
    public void loggingPointcut(){
    }

    /**
     * Pointcut that matches all Spring beans in the application's main packages.
     */

    @Pointcut(("within(com.example.servingwebcontent..*)" +
            " || within(com.example.servingwebcontent.service..*)" +
            " || within(com.example.servingwebcontent.controller..*)"))
    public void applicationPackagePointcut(){
    }

    /**
     * Advice that logs methods throwing exceptions.
     */

    @AfterThrowing(pointcut = "applicationPackagePointcut() && loggingPointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : "NULL");
    }

}
