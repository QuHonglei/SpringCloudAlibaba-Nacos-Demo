package com.itheibai.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Order(-1)  // 配置过滤器权重，数字越小优先级越高，也可以实现Ordered
@Component
public class AuthorizeFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 从上下文中获取请求
        ServerHttpRequest request = exchange.getRequest();
        // 获取请求参数
        MultiValueMap<String, String> params = request.getQueryParams();
        // 从参数中查找 authorizetion 参数
        String authorizetion = params.getFirst("authorizetion");
        // 判断是否等于 admin
        if ("admin".equals(authorizetion)){
            // 是，放行,
            return chain.filter(exchange);
        }

        // 否，拦截，拦截之前可以先设置一个返回状态码

        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();

        // 测试访问 http://localhost:10010/sys/user/getUserById?id=1&authorizetion=admin
    }
}
