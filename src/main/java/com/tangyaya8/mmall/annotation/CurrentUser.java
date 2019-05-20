package com.tangyaya8.mmall.annotation;

import java.lang.annotation.*;

/**
 * @author tangxuejun
 * 用于方法级别的获取当前用户
 * @version 2019-05-11 10:04
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
    String value() default "user";
}
