package com.tangyaya8.mmall.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.tangyaya8.mmall.common.Resp;
import com.tangyaya8.mmall.pojo.User;
import com.tangyaya8.mmall.utils.CookieUtils;
import com.tangyaya8.mmall.web.context.UserContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

import static com.tangyaya8.mmall.common.ErrCode.USER_NOT_LOGIN;

/**
 * @author tangxuejun
 * 登录校验拦截器
 * @version 2019-05-11 22:48
 */
@Component
@Slf4j
@ConditionalOnProperty(name = "valid-user", havingValue = "true")
public class ValidAuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User currentUser = UserContextHolder.getCurrentUser();
        if (currentUser == null) {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(Charsets.UTF_8.name());
            PrintWriter writer = response.getWriter();
            writer.println(Strings.nullToEmpty(JSON.toJSONString(Resp.error(USER_NOT_LOGIN))));
            writer.flush();
            return false;
        } else {
            //给cookie续命
            CookieUtils.setLoginUserToCookie(response, currentUser);
            return true;
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        if (ex != null) {
            log.error("请求发生异常, uri : {} , status:{} ", request.getRequestURI(), response.getStatus(), ex);
        }
    }
}
