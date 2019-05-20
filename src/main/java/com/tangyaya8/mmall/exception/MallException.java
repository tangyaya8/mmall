package com.tangyaya8.mmall.exception;

import com.tangyaya8.mmall.common.ErrCode;
import lombok.Getter;

/**
 * @author tangxuejun
 * @version 2019-05-07 14:49
 */
@Getter
public class MallException extends Exception {
    private int code;
    private String errMsg;

    public MallException() {
        this.code = ErrCode.INTERNAL_ERROR.getCode();
        this.errMsg = ErrCode.INTERNAL_ERROR.getMsg();
    }

    public MallException(String message) {
        super(message);
        this.code = ErrCode.INTERNAL_ERROR.getCode();
        this.errMsg = message;
    }

    public MallException(ErrCode errCode) {
        super(errCode.getMsg());
        this.code = errCode.getCode();
        this.errMsg = errCode.getMsg();
    }

    public MallException(String message, int code) {
        super(message);
        this.errMsg = message;
        this.code = code;
    }
}
