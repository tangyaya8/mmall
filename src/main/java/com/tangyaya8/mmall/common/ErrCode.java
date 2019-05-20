package com.tangyaya8.mmall.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author tangxuejun
 * @version 2019-05-07 09:50
 * 只有返回值为0的时候是成功的，其他都为失败
 */
@AllArgsConstructor
@Getter
public enum ErrCode {

    SUCCESS(0, "SUCCESS"),

    INTERNAL_ERROR(1, "内部错误"),
    ILLEGAL_PARAM(2, "参数错误"),


    LOGIN_ERROR(101, "用户名或密码错误"),
    USERNAME_EXISTS(102, "用户名已经存在"),
    EMAIL_EXISTS(103, "邮箱已经存在"),
    USER_NOT_LOGIN(104, "用户未登录"),
    USER_NOT_EXISTS(105, "用户不存在"),
    QUESTION_NOT_EXISTS(106, "问题为空"),
    ANSWER_NOT_CONFIRM_QUESTION(107, "问题和答案不符"),
    LOGIN_ADMIN_FORBIDDEN(108, "非管理员,禁止登陆"),
    SIGN_ERROR(109, "签名错误"),
    ;


    private int code;
    private String msg;

}
