package com.small.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

// aopexam
// Advice + joinpoint + pointcut
@Aspect
@Component
public class ServiceMonitor {
    // @Befre, @Afeter, @AfterThrowing
    @AfterReturning("execution(* examples..*Service.*(..))")
    public void logServiceAccess(JoinPoint joinPoint){
        System.out.println("----------------------------------");
        System.out.println(joinPoint.getSignature().getName());
        System.out.println(joinPoint.getTarget().getClass().getName());
        System.out.println("----------------------------------");
    }

    @AfterThrowing(value = "execution(* examples..*Service.*(..))",
            throwing = "ex")
    public void catchException(JoinPoint joinPoint, Exception ex){
        System.out.println("catchException----------------------------------");
        System.out.println(joinPoint.getSignature().getName());
        System.out.println(joinPoint.getTarget().getClass().getName());
        System.out.println("ex :" + ex.getMessage());
        System.out.println("catchException----------------------------------");
    }
}