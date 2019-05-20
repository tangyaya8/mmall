package com.tangyaya8.mmall.web.context;

import com.tangyaya8.mmall.pojo.User;

/**
 * @author tangxuejun
 * @version 2019-05-09 13:02
 */
public class UserContextHolder {
    private static final ThreadLocal<User> USER_CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setUserContextHolder(User user) {
        if (user == null) {
            clearUserContextHolder();
            return;
        }
        USER_CONTEXT_HOLDER.set(user);
    }

    public static User getCurrentUser() {
        return USER_CONTEXT_HOLDER.get();
    }

    public static void clearUserContextHolder() {
        USER_CONTEXT_HOLDER.remove();
    }
}
