package com.tangyaya8.mmall.common;

import lombok.Getter;

import java.io.Serializable;

/**
 * @author tangxuejun
 * @version 2019-05-07 09:42
 */
@Getter
public class Resp<T> implements Serializable {
    private int code;
    private String msg;
    private T data;

    public static <T> Resp<T> success(T data) {
        Resp<T> resp = new Resp<>();
        resp.code = ErrCode.SUCCESS.getCode();
        resp.msg = ErrCode.SUCCESS.getMsg();
        resp.data = data;
        return resp;
    }


    public static <T> Resp<T> error(ErrCode errCode) {
        Resp<T> resp = new Resp<>();
        resp.msg = errCode.getMsg();
        resp.code = errCode.getCode();
        return resp;
    }

    public static <T> Resp<T> error(String msg) {
        Resp<T> resp = new Resp<>();
        resp.msg = msg;
        resp.code = ErrCode.INTERNAL_ERROR.getCode();
        return resp;
    }


    public static <T> Resp<T> error(int code, String msg) {
        Resp<T> resp = new Resp<>();
        resp.msg = msg;
        resp.code = code;
        return resp;
    }

    public static <T> Resp<T> success() {
        return success(null);
    }

}
