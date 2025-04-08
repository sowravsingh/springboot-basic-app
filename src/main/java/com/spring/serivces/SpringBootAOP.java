package com.spring.serivces;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SpringBootAOP {



    @Before("execution(* com.spring.controllers.DemoController2.*(*))")
    public void BeforeMethod(){
        System.out.println(" AOP Before execution method was called");
    }

    @Before("within(com.spring.controllers.*)")
    public void BeforeWithinMethod(){
        System.out.println(" AOP Before  within methode was called");
    }

    @Before("@within(org.springframework.web.bind.annotation.RestController)")
    public void BeforeWithinAnnotationMethod(){
        System.out.println(" AOP Before  within Annotation methode was called");
    }

    @Before("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void BeforeattheRateAnnotationMethod(){
        System.out.println(" AOP Before  @ Annotation methode was called");
    }

    @Before("args(String,int)")
    public void BeforeArgsMethod(){
        System.out.println(" AOP Before  Args methode was called");
    }


    @Around("@annotation(com.spring.components.CustomAnnotation)")
    public void AroundCustomAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Doing something before user service class");
        joinPoint.proceed();
        System.out.println("completed intercepting in user service class");
    }

//    @Before("@args(org.springframework.stereotype.Service)")
//    public void BeforeArgsAnnotationMethod(){
//        System.out.println(" AOP Before @ Args methode was called");
//    }


//    @Around("within(com.spring.controllers.*)")
//    public void AroundWithinMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//        System.out.println(" AOP around  within methode was called");
//        joinPoint.proceed();
//        System.out.println(" AOP around within method was finished");
//    }
}
