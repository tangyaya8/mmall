package com.tangyaya8.mmall.config;

import com.tangyaya8.mmall.web.listener.UserContextListener;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tangxuejun
 * @version 2019-05-09 13:10
 */
@Configuration
public class ServletListenerConfig {
    @Bean
    public ServletListenerRegistrationBean userContextListener() {
        ServletListenerRegistrationBean<UserContextListener> registrationBean = new ServletListenerRegistrationBean<>();
        UserContextListener listener = new UserContextListener();
        registrationBean.setListener(listener);
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
