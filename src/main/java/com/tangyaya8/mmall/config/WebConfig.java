package com.tangyaya8.mmall.config;

import com.tangyaya8.mmall.handler.UserResolver;
import com.tangyaya8.mmall.web.interceptor.ValidAuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author tangxuejun
 * @version 2019-05-11 10:15
 */
@Configuration
@ConditionalOnBean(ValidAuthInterceptor.class)
public class WebConfig implements WebMvcConfigurer {
    private final UserResolver userResolver;
    private final ValidAuthInterceptor validAuthInterceptor;

    @Autowired
    public WebConfig(UserResolver userResolver, ValidAuthInterceptor validAuthInterceptor) {
        this.userResolver = userResolver;
        this.validAuthInterceptor = validAuthInterceptor;
    }

    //注册自定义
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(userResolver);
    }

    /**
     * 添加自登录/为cookie拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(validAuthInterceptor)
                .excludePathPatterns("/user/login","/manager/user/login");
    }

    /**
     * 添加跨域的支持
     *
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "PUT", "DELETE");
    }
}
