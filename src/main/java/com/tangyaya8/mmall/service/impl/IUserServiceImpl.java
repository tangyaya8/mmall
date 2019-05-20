package com.tangyaya8.mmall.service.impl;

import com.tangyaya8.mmall.common.TokenCache;
import com.tangyaya8.mmall.exception.MallException;
import com.tangyaya8.mmall.pojo.User;
import com.tangyaya8.mmall.repository.jpa.UserRepository;
import com.tangyaya8.mmall.service.IUserService;
import com.tangyaya8.mmall.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.tangyaya8.mmall.common.Const.EMAIL_TYPE;
import static com.tangyaya8.mmall.common.Const.USERNAME_TYPE;
import static com.tangyaya8.mmall.common.ErrCode.*;
import static com.tangyaya8.mmall.common.UserRole.CUSTOMER;

/**
 * @author tangxuejun
 * @version 2019-05-07 09:14
 */
@Service
@Slf4j
public class IUserServiceImpl implements IUserService {
    private final UserRepository userRepository;

    private static final String TOKEN_PREFIX = "token_";

    public IUserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        password = MD5Util.getMD5String(password);
        User user = userRepository.getUserByUserNameAndPassword(username, password);
        if (user != null) {
            user.setPassword(StringUtils.EMPTY);
        }
        return user;
    }

    @Override
    public void register(User user) throws MallException {
        if (user == null) {
            throw new MallException(ILLEGAL_PARAM);
        }
        //校验用户名称
        this.validUser(user.getUserName(), USERNAME_TYPE);
        //校验email
        this.validUser(user.getEmail(), EMAIL_TYPE);
        //设置为普通用户的角色
        user.setRole(CUSTOMER);
        //md5加密
        user.setPassword(MD5Util.getMD5String(user.getPassword()));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new MallException(e.getMessage(), INTERNAL_ERROR.getCode());
        }
    }

    @Override
    public void validUser(String obj, String type) throws MallException {
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(obj)) {
            //校验用户名是否存在
            if (USERNAME_TYPE.equals(type)) {
                boolean existUsername = userRepository.existsByUserName(obj);
                if (existUsername) {
                    throw new MallException(USERNAME_EXISTS);
                }
            }
            //校验email是否存在
            if (EMAIL_TYPE.equals(type)) {
                boolean existsEmail = userRepository.existsByEmail(obj);
                if (existsEmail) {
                    throw new MallException(EMAIL_EXISTS);
                }
            }
        } else {
            throw new MallException(ILLEGAL_PARAM);
        }
    }

    public String selectQuestion(String userName) throws MallException {
        boolean existUsername = userRepository.existsByUserName(userName);
        //如果用户不存在
        if (!existUsername) {
            throw new MallException(USER_NOT_EXISTS);
        }
        String question = userRepository.selectQuestionByUserName(userName);
        if (StringUtils.isNotBlank(question)) {
            return question;
        }
        throw new MallException(QUESTION_NOT_EXISTS);
    }

    @Override
    public String checkForgetAnswer(String userName, String question, String answer) throws MallException {
        long count = userRepository.countByUserNameAndQuestionAndAnswer(userName, question, answer);
        if (count > 0) {
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TOKEN_PREFIX + userName, forgetToken);
            return forgetToken;
        }
        throw new MallException(ANSWER_NOT_CONFIRM_QUESTION);
    }

    @Override
    public void forgetRestPassword(String userName, String password, String forgetToken) throws MallException {
        if (StringUtils.isBlank(forgetToken)) {
            throw new MallException(ILLEGAL_PARAM);
        }
        boolean isNotExists = userRepository.existsByUserName(userName);
        //如果用户不存在
        if (!isNotExists) {
            throw new MallException(USER_NOT_EXISTS);
        }
        String token = TokenCache.getKey(TOKEN_PREFIX + userName);

        if (StringUtils.equals(token, forgetToken)) {
            throw new MallException(ILLEGAL_PARAM);
        }
        String newPassword = MD5Util.getMD5String(password);
        long row = userRepository.updatePassword(newPassword, userName);
        if (row < 0) {
            throw new MallException(INTERNAL_ERROR);
        }
    }

    @Override
    public void resetPassword(String oldPassword, String newPassword, User user) throws MallException {
        long rowCount = userRepository.countByPasswordAndId(oldPassword, user.getId());
        if (rowCount < 0) {
            throw new MallException("旧密码错误");
        }
        long row = userRepository.resetPassword(newPassword, user.getId());
        if (row < 0) {
            throw new MallException(INTERNAL_ERROR);
        }
    }

    @Override
    public User updateUserInfo(User user) throws MallException {
        if (user == null) {
            throw new MallException(ILLEGAL_PARAM);
        }
        //校验email
        String originEmail = user.getEmail();
        //是否是当前用户的
        long count = userRepository.existsByEmailAndId(originEmail, user.getId());
        if (count > 0) {
            throw new MallException(EMAIL_EXISTS);
        }
        //如果不存在直接修改
        return userRepository.save(user);
    }

    @Override
    public User getUserInfo(long id) {
        User user = userRepository.getOne(id);
        user.setPassword(StringUtils.EMPTY);
        System.out.println(user);
        return user;
    }
}
