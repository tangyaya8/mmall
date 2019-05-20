package com.tangyaya8.mmall.utils;


import com.alibaba.fastjson.JSON;
import com.tangyaya8.mmall.pojo.User;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.SecretKey;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.tangyaya8.mmall.config.CookieConfig.COOKIE_MAX_AGE;
import static com.tangyaya8.mmall.config.CookieConfig.COOKIE_PATH;

/**
 * @author tangxuejun
 * @version 2019-05-08 18:19
 */
public class CookieUtils {
    private final static String SALT = "CAFE_BABY";
    private final static String SIGN = "sign";
    private final static String USER_INFO = "user";
    //将json user加密然后写到cookie中
    private final static SecretKey SECRET_KEY = AESUtils.createKey("TANGYAYA_8");


    public static User getCurrentUserFromCookie(HttpServletRequest request) {
        return getCurrentUserFromCookie(request.getCookies());
    }

    public static User getCurrentUserFromCookie(Cookie[] cookies) {
        String sign = null;
        String userObj = null;
        if (cookies == null) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName() == null) {
                continue;
            }
            String value = URLDecoder.decode(cookie.getValue(), StandardCharsets.UTF_8);
            switch (cookie.getName()) {
                case SIGN:
                    sign = value;
                    break;
                case USER_INFO:
                    userObj = value;
                    break;
                default:
                    break;
            }
        }
        if (StringUtils.isBlank(sign)
                || StringUtils.isBlank(userObj)) {
            return null;
        }
        //校验用户信息是否被修改
        String token = MD5Util.getMD5String(userObj + SALT);
        if (!StringUtils.equals(sign, token)) {
            return null;
        }
        //解密user
        String deSecretObj = new String(AESUtils.decrypt(SECRET_KEY, userObj.getBytes(StandardCharsets.ISO_8859_1)));
        return JSON.parseObject(deSecretObj, User.class);
    }

    public static void setLoginUserToCookie(HttpServletResponse resp, User user) {
        //将用户相关信息放入cookie
        String userObj = JSON.toJSONString(user);
        byte[] decrypt = AESUtils.encrypt(SECRET_KEY, userObj.getBytes());
        //要将字符序列化编码成ISO-8859,utf-8可能序列两次不一样
        String token = new String(decrypt, StandardCharsets.ISO_8859_1);
        resp.addCookie(assembleCookie(USER_INFO, token, COOKIE_MAX_AGE));
        System.out.println(SECRET_KEY);
        //生成TOKEN放入cookie
        String sign = MD5Util.getMD5String(token + SALT);
        resp.addCookie(assembleCookie(SIGN, sign, COOKIE_MAX_AGE));
    }


    private static Cookie assembleCookie(String key, String value, int expired) {
        if (StringUtils.isNotBlank(value)) {
            value = URLEncoder.encode(value, StandardCharsets.UTF_8);
        }

        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(expired);
        cookie.setPath(COOKIE_PATH);
        return cookie;
    }

    public static void removeLoginUserFromCookie(HttpServletResponse resp) {
        resp.addCookie(assembleCookie(USER_INFO, "", 0));
        resp.addCookie(assembleCookie(SIGN, "", 0));
    }
}
