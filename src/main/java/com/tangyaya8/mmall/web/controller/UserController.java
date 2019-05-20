package com.tangyaya8.mmall.web.controller;

import com.tangyaya8.mmall.annotation.CurrentUser;
import com.tangyaya8.mmall.common.ErrCode;
import com.tangyaya8.mmall.common.Resp;
import com.tangyaya8.mmall.exception.MallException;
import com.tangyaya8.mmall.pojo.User;
import com.tangyaya8.mmall.service.IUserService;
import com.tangyaya8.mmall.utils.CookieUtils;
import com.tangyaya8.mmall.web.context.UserContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author tangxuejun
 * @version 2019-05-07 09:08
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Resp<User> login(String userName, String password, HttpServletResponse response) {
        User user = userService.login(userName, password);
        if (user != null) {
            CookieUtils.setLoginUserToCookie(response, user);
            return Resp.success(user);
        } else {
            return Resp.error(ErrCode.LOGIN_ERROR);
        }
    }

    @GetMapping("/logout")
    public Resp<String> logout(HttpServletResponse response) {
        CookieUtils.removeLoginUserFromCookie(response);
        return Resp.success();
    }

    @PostMapping("/register")
    public Resp<String> register(@Validated User user) throws MallException {
        userService.register(user);
        return Resp.success();
    }

    @PostMapping("/valid")
    public Resp<String> validUser(String obj, String type) throws MallException {
        userService.validUser(obj, type);
        return Resp.success();
    }

    @GetMapping("/getInfo")
    public Resp<User> getUser() {
        return Resp.success(UserContextHolder.getCurrentUser());
    }

    @GetMapping("/getForgetQuestion")
    public Resp<String> forgetQuestion(String userName) throws MallException {
        return Resp.success(userService.selectQuestion(userName));
    }

    @GetMapping("/checkForgetAnswer")
    public Resp<String> checkForgetAnswer(String userName, String question, String answer) throws MallException {
        String token = userService.checkForgetAnswer(userName, question, answer);
        return Resp.success(token);
    }

    @PostMapping("/restForgetPassword")
    public Resp<String> forgetRestPassword(String userName, String password, String forgetToken) throws MallException {
        userService.forgetRestPassword(userName, password, forgetToken);
        return Resp.success();
    }

    @PostMapping("/restPassword")
    public Resp<String> resetPassword(String oldPassword, String newPassword) throws MallException {
        User user = UserContextHolder.getCurrentUser();
        userService.resetPassword(oldPassword, newPassword, user);
        return Resp.success();
    }

    @PostMapping("/updateUserInfo")
    public Resp<User> updateUserInfo(User user, HttpServletResponse response) throws MallException {
        User currentUser = UserContextHolder.getCurrentUser();
        //防止越权
        user.setId(currentUser.getId());
        User updateUser = userService.updateUserInfo(user);
        CookieUtils.setLoginUserToCookie(response, updateUser);
        return Resp.success(updateUser);
    }
    @GetMapping("/getUserInfo")
    public Resp<User> getUserInfo(@CurrentUser User currentUser) {
        User user = userService.getUserInfo(currentUser.getId());
        return Resp.success(user);
    }

}
