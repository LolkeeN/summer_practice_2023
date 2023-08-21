package com.vasyl.summer.practice.aspect;

import com.vasyl.summer.practice.annotation.UserId;
import com.vasyl.summer.practice.configs.security.jwt.JwtUser;
import com.vasyl.summer.practice.exceptions.InternalViolationException;
import com.vasyl.summer.practice.exceptions.InternalViolationType;
import com.vasyl.summer.practice.exceptions.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import static com.vasyl.summer.practice.aspect.AspectUtilityClass.getAnnotations;


@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class IdAspect {

    private final HttpServletRequest request;

    @SneakyThrows
    @Around("execution(public * *(.., @com.vasyl.summer.practice.annotation.UserId (*), ..))")
    public Object interceptMethodsWithAnnotatedParameters(ProceedingJoinPoint joinPoint) {
        Annotation[][] annotations = getAnnotations(joinPoint);
        Object[] newArgs = getUserIdNewArg(joinPoint, annotations);
        return joinPoint.proceed(newArgs);
    }

    private Object[] getUserIdNewArg(ProceedingJoinPoint joinPoint, Annotation[][] annotations) throws UnauthorizedException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        int i = 0;
        Object[] newArgs = new Object[joinPoint.getArgs().length];
        for (Object arg : joinPoint.getArgs()) {
            for (Annotation annotation : annotations[i]) {
                if (annotation.annotationType() == UserId.class) {
                    UserId userIdAnnotation = (UserId) annotation;
                    if (!userIdAnnotation.required() && authentication.getPrincipal().equals("anonymousUser")) {
                        newArgs[i] = null;
                    } else if (userIdAnnotation.required() && authentication.getPrincipal().equals("anonymousUser")) {
                        throw new UnauthorizedException();
                    } else {
                        newArgs[i] = getUserId(authentication);
                    }
                } else {
                    newArgs[i] = arg;
                }
            }
            i++;
        }
        return newArgs;
    }


    private String getUserId(Authentication authentication) {
        JwtUser user = (JwtUser) authentication.getPrincipal();
        return user.getId();
    }
}
