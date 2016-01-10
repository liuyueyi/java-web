package com.mogu.hui.study.annotation;

import com.mogu.hui.study.Constants.RoleLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
* Created by yihui on 16/1/9.
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.LOCAL_VARIABLE})
public @interface Role {
    RoleLevel level() default RoleLevel.DEFAULT;
}
