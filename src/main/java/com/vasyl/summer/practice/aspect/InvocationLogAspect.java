package com.vasyl.summer.practice.aspect;

import com.vasyl.summer.practice.annotation.InvocationLog;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class InvocationLogAspect {

    @SneakyThrows
    @Around("execution(* *(..)) && @annotation(com.vasyl.summer.practice.annotation.InvocationLog)")
    public Object interceptMethodsWithAnnotatedParameters(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        logControllerMethodInvocation(joinPoint.getThis(), joinPoint.getSignature().getName(), joinPoint.getArgs(), signature.getMethod().getAnnotation(
                InvocationLog.class).level());
        return joinPoint.proceed();
    }

    private void logControllerMethodInvocation(Object joinPointThis, String methodName, Object[] args, LogLevel logLevel) {
        if (logLevel == LogLevel.INFO) {
            log.info("{} {} method was called with arguments: {}", joinPointThis, methodName, args);
        }else {
            log.debug("{} {} method was called with arguments: {}", joinPointThis, methodName, args);
        }
    }

}
