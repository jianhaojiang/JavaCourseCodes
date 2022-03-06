## Redis与Spring整合

![image-20220306111313798](https://cdn.jsdelivr.net/gh/jianhaojiang/PicGoBed/img/20220306111320.png)

安装示例编写测试代码，访问接口，对不同情况进行接口压测，测试缓存效果

### 未配置任何缓存，SpringBoot+Mybatis 

使用 wrk 进行接口压测 并发80 压测30秒

sb -u http://127.0.0.1:8099/account/list -c 80 -N 30

**结果：**每秒响应请求9.7 平均响应时间为79.7ms

![image-20220306112654799](https://cdn.jsdelivr.net/gh/jianhaojiang/PicGoBed/img/20220306112654.png)



### Ecache + Mybatis 二级缓存

#### 配置 Mybatis Ecache 二级缓存

使用 wrk 进行接口压测 并发80 压测30秒

sb -u http://127.0.0.1:8099/account/list -c 80 -N 30

**结果：**每秒响应请求791.4 平均响应时间为26.1ms ；比未走缓存每秒响应提升很大，响应时间也很小

![image-20220306155242055](https://cdn.jsdelivr.net/gh/jianhaojiang/PicGoBed/img/20220306155249.png)



### 配置Spring Cache + Ecache 缓存 实现方法级别缓存

比配置Mybatis二级缓存要快，目测是减少了走mybatis的步骤

![image-20220306155726256](https://cdn.jsdelivr.net/gh/jianhaojiang/PicGoBed/img/20220306173246.png)

#### 同时配置Mybatis二级缓存和Spring Ecache缓存

和仅仅配置Ecache缓存差不多，因为缓存基本百分百命中，故和配置Mybatis二级缓存关系不大

![image-20220306160346529](https://cdn.jsdelivr.net/gh/jianhaojiang/PicGoBed/img/20220306160346.png)

### Spring Cache 使用 Redis 代替 Ecache 实现缓存

#### 使用本地 Redis 

比起Ecache 慢了一些，但是基本在一个级别

![image-20220306172043494](https://cdn.jsdelivr.net/gh/jianhaojiang/PicGoBed/img/20220306172043.png)

#### 使用服务器 Redis

由于网络原因，平均响应比本地要慢上一些。

![image-20220306173205248](https://cdn.jsdelivr.net/gh/jianhaojiang/PicGoBed/img/20220306173205.png)



## 总结

前提：本地环境，使用 wrk 进行接口压测 并发80 压测30秒

**平均响应速度：**

- Spring Cache 整合 Ecache 是最快的，直接缓存到内存进行读取，缓存命中率百分百。（18.1ms）
- 使用 Ecache 实现 Mybatis 二级缓存平均响应速度（26.1ms）
- Spring Cache整合本地 Reids 进行缓存平均响应速度（28.2ms），个人阿里云服务器（38.7ms）
- 不使用缓存 平均响应速度（79.7ms）

Redis相比Ecache虽然慢一些，但在可接受范围，差别不大；单机应用缓存选择Ecache，多个服务器共享缓存可使用Redis进行缓存数据。实际使用可参考缓存对象进行使用

​	

​	

​	

​	