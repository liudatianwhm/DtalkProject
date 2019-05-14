package com.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xiaofeixia
 * @title: HttpConfig
 * @projectName eapp-corp-project
 * @description: TODO http配置类
 * @date 2019/5/1016:07
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "http")
public class HttpConfig {

    private String userUrl;

    private String statusUrl;
}
