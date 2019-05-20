package com.tangyaya8.mmall.service;

import com.tangyaya8.mmall.exception.MallException;
import com.tangyaya8.mmall.pojo.User;

/**
 * @author tangxuejun
 * @version 2019-05-07 09:12
 */
public interface IUserService {
    User login(String username, String password);

    void register(User user) throws MallException;

    void validUser(String obj, String type) throws MallException;

    String selectQuestion(String userName) throws MallException;

    String checkForgetAnswer(String userName, String question, String answer) throws MallException;

    void forgetRestPassword(String userName, String password, String forgetToken) throws MallException;

    void resetPassword(String oldPassword, String newPassword, User user) throws MallException;

    User updateUserInfo(User user) throws MallException;

    User getUserInfo(long id);
}

