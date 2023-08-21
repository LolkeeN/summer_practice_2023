package com.vasyl.summer.practice.aspect;

import java.lang.annotation.Annotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.SoftException;
import org.aspectj.lang.reflect.MethodSignature;

public class AspectUtilityClass {

    public static Annotation[][] getAnnotations(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();
        Annotation[][] annotations;
        try {
            annotations = joinPoint.getTarget().getClass().
                    getMethod(methodName, parameterTypes).getParameterAnnotations();
        } catch (Exception e) {
            throw new SoftException(e);
        }
        return annotations;
    }
}
