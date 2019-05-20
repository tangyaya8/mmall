package com.tangyaya8.mmall.web.listener;

import com.tangyaya8.mmall.web.context.UserContextHolder;
import com.tangyaya8.mmall.utils.CookieUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

/**
 * @author tangxuejun
 * @version 2019-05-09 12:56
 */
public class UserContextListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        UserContextHolder.clearUserContextHolder();
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

        ServletRequest req = sre.getServletRequest();
        if (req instanceof HttpServletRequest) {
            HttpServletRequest request = (HttpServletRequest) req;
            UserContextHolder.setUserContextHolder(CookieUtils.getCurrentUserFromCookie(request));
        }
    }
}
