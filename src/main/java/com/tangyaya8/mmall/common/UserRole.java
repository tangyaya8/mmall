package com.tangyaya8.mmall.common;

import lombok.AllArgsConstructor;

/**
 * @author tangxuejun
 * @version 2019-05-07 15:05
 */
@AllArgsConstructor
public enum UserRole {
    ADMIN(0),
    CUSTOMER(1),
    ;
    private int role;
}
