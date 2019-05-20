package com.tangyaya8.mmall.handler;

import com.tangyaya8.mmall.common.ErrCode;
import com.tangyaya8.mmall.common.Resp;
import com.tangyaya8.mmall.exception.MallException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author tangxuejun
 * 全局异常处理
 * @version 2019-05-09 17:17
 * ConditionalOnProperty 是异常处理的开关
 * 在application.yml中可以更改相关的配置：
 * true是启用全局处理异常，false是关闭
 */
@RestControllerAdvice
@ConditionalOnProperty(name = "tangbaobao.spring.bean.load.global-exception", havingValue = "true")
@Slf4j
public class MallExceptionHandler {
    /**
     * 格式化Exception，捕捉参数校验异常。
     * 异常详细信息请见：{@link MethodArgumentNotValidException}
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public Resp<String> handleBindException(BindException e) {
        log.error(String.valueOf(e));
        return Resp.error(ErrCode.ILLEGAL_PARAM.getCode(), toStr(e));
    }


    @ExceptionHandler(MallException.class)
    public Resp<String> handlerMallException(MallException e) {
        log.error(String.valueOf(e));
        return Resp.error(e.getCode(), e.getErrMsg());
    }


    /**
     * 默认错误处理器
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Resp<String> HandDefaultException(Exception e) {
        log.error(String.valueOf(e));
        if (e instanceof HttpRequestMethodNotSupportedException) {
            HttpRequestMethodNotSupportedException exception = (HttpRequestMethodNotSupportedException) e;
            return Resp.error(HttpStatus.METHOD_NOT_ALLOWED.value(), exception.getMessage());
        }
        if (e instanceof MallException) {
            MallException exception = (MallException) e;
            return Resp.error(exception.getCode(), exception.getErrMsg());
        }

        return Resp.error(ErrCode.INTERNAL_ERROR);
    }

    private String toStr(BindException e) {
        return e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(x -> (FieldError) x)
                .map(err -> err.getField() + ":" + err.getDefaultMessage())
                .findFirst()
                .map(String::trim)
                .get();
    }


}
