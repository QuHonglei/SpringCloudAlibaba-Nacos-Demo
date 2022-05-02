package com.itheibai.order;

import com.itheibai.feign.clients.UserClient;
import com.itheibai.feign.config.DefaultFeignConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient //让nacos可以扫描到服务
@EnableFeignClients(clients = UserClient.class,defaultConfiguration = DefaultFeignConfiguration.class) // 开启feign远程调用,指定全局配置
@MapperScan("com.itheibai.order.sys.dao")
@SpringBootApplication
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class,args);
    }

    @Bean
    @LoadBalanced   // 默认负载均衡策略为：轮询
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
