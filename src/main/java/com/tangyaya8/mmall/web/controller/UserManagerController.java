package com.tangyaya8.mmall.web.controller;

import com.tangyaya8.mmall.common.ErrCode;
import com.tangyaya8.mmall.common.Resp;
import com.tangyaya8.mmall.common.UserRole;
import com.tangyaya8.mmall.pojo.User;
import com.tangyaya8.mmall.service.IUserService;
import com.tangyaya8.mmall.utils.CookieUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author tangxuejun
 * @version 2019-05-08 14:10
 */
@RestController
@RequestMapping("/manager/user")
public class UserManagerController {
    private final IUserService userService;

    public UserManagerController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Resp<User> login(HttpServletResponse response, String userName, String password) {
        User user = userService.login(userName, password);
        if (user == null) {
            return Resp.error(ErrCode.LOGIN_ERROR);
        }
        if (user.getRole() == UserRole.ADMIN) {
            CookieUtils.setLoginUserToCookie(response, user);
            return Resp.success(user);
        } else {
            return Resp.error(ErrCode.LOGIN_ADMIN_FORBIDDEN);
        }
    }
}
