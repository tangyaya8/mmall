package com.tangyaya8.mmall.web.aspect;

import com.tangyaya8.mmall.exception.MallException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * @author tangxuejun
 * 建档对象方法级别参数校验
 * @version 2019-05-11 19:20
 */
@Aspect
@Component
public class ValidAspect {
    //切入点为controller层
    @Pointcut("execution(public * com.tangyaya8.mmall.web.controller.*.*(..))")
    public void valid() {
    }

    //前置通知
    @Before("valid()")
    public Object around(JoinPoint pjp) throws MallException {
        Object[] objects = pjp.getArgs();
        //  获得切入目标对象
        Object target = pjp.getThis();
        // 获得切入的方法
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        // 执行校验，获得校验结果
        Set<ConstraintViolation<Object>> validResult = validMethodParams(target, method, objects);
        //如果有校验不通过的
        if (!validResult.isEmpty()) {
            //返回第一条
            throw new MallException(validResult.iterator().next().getMessage());
        }
        return null;
    }

    private <T> Set<ConstraintViolation<T>> validMethodParams(T obj, Method method, Object[] params) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        ExecutableValidator validator = factory.getValidator().forExecutables();
        return validator.validateParameters(obj, method, params);
    }
}
