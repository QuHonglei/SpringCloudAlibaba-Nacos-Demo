package com.itheibai.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@Data
@Component // 注册到spring容器
@ConfigurationProperties(prefix = "pattern") // 读取配置文件的属性前缀
public class PatternProperties {
    private String dateformat;

    /*
    多环境配置属性
     */
    private String envSharedValue;
}
