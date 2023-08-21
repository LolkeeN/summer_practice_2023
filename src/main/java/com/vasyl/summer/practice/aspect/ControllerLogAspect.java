package com.vasyl.summer.practice.aspect;

import com.vasyl.summer.practice.annotation.ControllerInvocationLog;
import com.vasyl.summer.practice.configs.security.jwt.JwtUser;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import static com.vasyl.summer.practice.aspect.AspectUtilityClass.getAnnotations;


@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class ControllerLogAspect {

    private final HttpServletRequest request;
    private LogLevel logLevel;

    @SneakyThrows
    @Around("execution(* *(..)) && @annotation(com.vasyl.summer.practice.annotation.ControllerInvocationLog)")
    public Object interceptMethodsWithAnnotatedParameters(ProceedingJoinPoint joinPoint) {
        Annotation[][] annotations = getAnnotations(joinPoint);
        logControllerMethodInvocation(getUserIdNewArg(joinPoint, annotations));
        return joinPoint.proceed();
    }

    private void logControllerMethodInvocation(String data) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (logLevel == LogLevel.INFO) {
            log.info("User with id {} made {} {} request and data: {}", getUserId(authentication), request.getMethod(),
                    request.getRequestURI(), data);
        } else {
            log.debug("User with id {} made {} {} request and data: {}", getUserId(authentication), request.getMethod(),
                    request.getRequestURI(), data);
        }
    }

    private String getUserIdNewArg(ProceedingJoinPoint joinPoint, Annotation[][] annotations) {
        int i = 0;
        StringBuilder result = new StringBuilder();
        for (Object arg : joinPoint.getArgs()) {
            for (Annotation annotation : annotations[i]) {
                if (annotation.annotationType() == RequestParam.class) {
                    result.append("Request Parameter: ").append(arg).append(" ");
                }
                if (annotation.annotationType() == RequestBody.class) {
                    result.append("Request Body: ").append(arg).append(" ");
                }
                if (annotation.annotationType() == ControllerInvocationLog.class) {
                    ControllerInvocationLog controllerInvocationLog = (ControllerInvocationLog) annotation;
                    logLevel = controllerInvocationLog.level();
                }
            }
            i++;
        }
        return result.toString();
    }

    private String getUserId(Authentication authentication) {
        try {
            JwtUser user = (JwtUser) authentication.getPrincipal();
            return user.getId();
        } catch (ClassCastException e) {
            return "anonymous user";
        }
    }
}
