package com.tangyaya8.mmall.web.controller;

import com.tangyaya8.mmall.common.Resp;
import com.tangyaya8.mmall.pojo.User;
import com.tangyaya8.mmall.web.context.UserContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangxuejun
 * @version 2019-05-15 21:46
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    public Resp<String> add(int count, int productId) {
        User currentUser = UserContextHolder.getCurrentUser();
        return null;
    }
}
