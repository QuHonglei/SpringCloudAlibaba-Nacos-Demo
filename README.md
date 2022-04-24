# SpringCloudAlibaba-Nacos-Demo
SpringCloud + MyBatis-plus + Nacos + Swagger + Mysql 分布式微服务搭建

### 一、创建父工程

1. ​         新建了maven项目

2. ​         引入父依赖

3. ​         引入springCloudAlibaba 依赖，从官网复制

4. ​         引入mysql驱动依赖

5. ​         引入mybatis-plus依赖

6. ​         引入lombok依赖

7. ​         引入druid依赖

8. ​         引入mybatis-plus代码生成器依赖



###  二、创建子工程（微服务）

1. ​         配置swagger
2. ​         配置druid
3. ​         自动生成代码
4. ​         编写业务接口
5. ​         用swagger测试
6. ​         然后同样的步骤在创建第二个子工程

### 三、远程调用

1. ​     在order-service（在启动类中注册，或新建一个配置类） 中注册 RestTemplate 实例
2. ​     修改order查询方法

### 四、整合Eureka

1. ​     创建 Eureka 模块子工程
2. ​     引入 Eureka 依赖(server)
3. ​     编写启动类
4. ​     编写配置文件yml
5. ​     配置服务名称
6. ​     配置 Eureka 地址

###  五、服务注册

1. ​     引入依赖（client）
2. ​     配置服务名称
3. ​     配置 Eureka 地址

### 六、服务发现

1. ​     将接口的ip地址改为服务名称
2. ​     在启动器 RestTemplate 实例上添加负载均衡注解 @LoadBalanced
   * 在nacos中使用此处的注解，需要导入新的负载均衡依赖，即@LoadBalanced的依赖

### 七、Nacos服务
1. ​     引入依赖（2021版本需要引入新的负载均衡依赖）
2. ​     注释掉Eureka依赖
3. ​     配置nacos在yml中，注释掉eureka配置
4. ​     使用nacos配置中心管理配置
5. ​     测试配置中心的文件是否注入进来：（此处是通过注解@Value的方式注入）
   * 新建bootstrap.yaml配置文件，此配置文件优先级略高于 application.yml
     * 配置加载 nacos 配置中心文件，及其他自定义配置
   * 由于 SpringCloudAlibaba 2021.0.1.0 没有 bootstrap 依赖，所以需要自己导入
   * 配置中心文件名称为：userService-dev.yaml
   * 内容：
   * pattern:
   * dateformat: yyyy-MM-dd HH:mm:ss
7. ​多环境共享配置（通过配置类注入）
   * 配置文件优先级为：userService-dev.yaml > userService.yaml（共享配置） > 本地配置
   * 在nacos配置文件中心新建 userService.yaml 共享配置文件
   * 新建属性配置类，读取配置文件属性，可能需要导入spring注解解释依赖
### 相关连接：**https://www.itheibai.com
