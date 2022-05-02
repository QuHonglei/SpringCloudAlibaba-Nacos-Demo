package com.itheibai.feign.clients;

import com.itheibai.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("userService") // 标识feign客户端接口，指定服务名称(userService)
public interface UserClient {

    @GetMapping("/sys/user/getUserById?id={id}")
    User findById(@PathVariable("id") Long id);
}
