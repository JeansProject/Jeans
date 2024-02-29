package com.jeans.cosmetic_project.common.aop;

import com.jeans.cosmetic_project.common.annotation.LoginId;
import com.jeans.cosmetic_project.user.dto.AuthenticatedUser;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TestAspect {

    @Around("execution(* com.jeans.cosmetic_project..controller.*.*(..))")
    public Object annotationTest(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Annotation and Aspect Test");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loginId = null;

        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            AuthenticatedUser user = (AuthenticatedUser) authentication.getPrincipal();
            if (user != null) {
                loginId = user.getUsername();
            }
        }

        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Parameter[] parameters = method.getParameters();

        for(int i = 0; i < parameters.length; i++) {
            if(parameters[i].isAnnotationPresent(LoginId.class)) {
                args[i] = loginId;
            }
        }

        Object result = joinPoint.proceed(args);
        return result;
    }

//    @AfterReturning(value = "@annotation(com.jeans.cosmetic_project.common.annotation.TestAnnotation) || execution(* com.jeans.cosmetic_project..controller.*.*(..))", returning = "result")
//    public void annotationTest(JoinPoint joinPoint, Object result) throws Throwable {
//        Object[] args = joinPoint.getArgs();
//        for(Object arg : args) {
//            if(arg != null) {
//                System.out.println("arg = " + arg.toString());
//                System.out.println("arg class = " + arg.getClass());
//            }
//
//        }
//        System.out.println("result = " + result);
//        System.out.println(args.toString());
//        System.out.println("Annotation and Aspect Test");
//
////        Object result = joinPoint.proceed();
////        return result;
//    }
}
