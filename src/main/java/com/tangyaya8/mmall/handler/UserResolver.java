package com.tangyaya8.mmall.handler;

import com.tangyaya8.mmall.annotation.CurrentUser;
import com.tangyaya8.mmall.utils.CookieUtils;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author tangxuejun
 * 支持注解绑定当前登录用户{@link CurrentUser}
 * @version 2019-05-11 09:45
 */
@Component
public class UserResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //从Cookie中获取User
        Optional<HttpServletRequest> request = Optional.ofNullable(webRequest.getNativeRequest(HttpServletRequest.class));
        return CookieUtils.getCurrentUserFromCookie(request
                .map(HttpServletRequest::getCookies)
                .orElse(null));
    }
}
