package com.itheibai.order.sys.controller;

import com.itheibai.feign.clients.UserClient;
import com.itheibai.feign.pojo.User;
import com.itheibai.order.sys.pojo.Order;
import com.itheibai.order.sys.pojo.OrderAndUserView;
import com.itheibai.order.sys.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itheibai
 * @since 2022-04-21
 */
@RestController
@RequestMapping("/sys/order")
@Api("订单控制类")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    UserClient userClient;

    /**
     * 根据订单id,查询信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据订单id,查询信息",response = Order.class,httpMethod = "GET") //方法注释
    @RequestMapping("/getOrderById")
    public Order getOrderById(Integer id) {
        return orderService.getById(id);
    }

    /**
     * 根据订单id,查询订单和用户信息
     * @param id
     * @return
     */
    @ApiOperation(value = "根据订单id,查询订单和用户信息",response = Order.class,httpMethod = "GET") //方法注释
    @RequestMapping("/getOrderAndUserViewById")
    public OrderAndUserView getOrderAndUserViewById(Integer id) {
        // 1.查询订单
        OrderAndUserView orderAndUserView = orderService.getOrderAndUserViewByIdById(id);
        // 2.查询用户
//        // 没有使用服务发现负载均衡之前
//        String url = "http://localhost:8080/sys/user/getUserById?id=" + orderAndUserView.getUserId();

        // 没有使用服务发现负载均衡之后
//        String url = "http://userService/sys/user/getUserById?id=" + orderAndUserView.getUserId();
//        User user = restTemplate.getForObject(url, User.class);

        // 使用feign发起http请求，查询用户
        User user = userClient.findById(orderAndUserView.getUserId());

        // 3.封装user信息
        orderAndUserView.setUser(user);
        // 4.返回
        return orderAndUserView;

    }
}
