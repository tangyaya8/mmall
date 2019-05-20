package com.tangyaya8.mmall.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author tangxuejun
 * @version 2019-05-11 10:45
 */
@Configuration
@ConfigurationProperties(prefix = "tangbaobao.spring.bean.load")
public class LoadBeanConfig {
    private String globalException;

    public void setGlobalException(String globalException) {
        this.globalException = globalException;
    }
}
