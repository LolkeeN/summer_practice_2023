package com.vasyl.summer.practice.annotation;

import com.vasyl.summer.practice.aspect.LogLevel;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface InvocationLog {
    LogLevel level() default LogLevel.INFO;

}
