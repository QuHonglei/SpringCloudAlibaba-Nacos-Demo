package com.itheibai.user.sys.controller;

import com.itheibai.user.sys.pojo.User;
import com.itheibai.user.sys.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author itheibai
 * @since 2022-04-21
 */
@Api(tags = "部门控制类") //类名注释
@Slf4j
@RestController
@RefreshScope // 配置nacos中心的配置文件热更新
@RequestMapping("/sys/user")
public class UserController {

    @Autowired
    UserService userService;

    @Value("${pattern.dateformat:}")  //注入的时候获取不到值，记得检查是否导入了bootstrap的依赖
    private String dateformat;

    /**
     * 测试配置中心的文件是否注入进来
     * 配置中心文件名称为：userService-dev.yaml
     * 内容：
     * pattern:
     *   dateformat: yyyy-MM-dd HH:mm:ss
     * @return
     */
    @RequestMapping("now")
    @ApiOperation(value = "查询时间信息",response = User.class,httpMethod = "GET") //方法注释
    public String now(){
        System.out.println(Locale.CHINA);
        System.out.println(dateformat);
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat, Locale.CHINA));
    }

    /**
     * 根据用户id,查询信息
     * @param id
     * @return
     */
    @ApiOperation(value = "查询用户信息",response = User.class,httpMethod = "GET") //方法注释
    @RequestMapping("/getUserById")
    public User getUserById(Integer id) {
        User user = userService.getById(id);
        return user;
    }

    /**
     * 模糊查询用户信息
     * @param keyword
     * @return
     */
    @ApiOperation(value = "模糊查询用户信息",response = User.class,httpMethod = "GET") //方法注释
    @RequestMapping("/getUserByKeyword")
    public List<User> getUserByKeyword(String keyword) {
        List<User> userList = userService.getByKeyword(keyword);
        return userList;
    }

}
