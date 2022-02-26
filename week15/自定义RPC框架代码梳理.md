RPC框架代码梳理
### 一、客户端
​	客户端通过调用 RPC 核心jar的client包下的Rpcfx.create方法，说明了要获取的类和RPC服务器的地址

![image-20220213100448884](https://cdn.jsdelivr.net/gh/jianhaojiang/PicGoBed/img/image-20220213100448884.png)

点开create方法，使用动态代理的方式，指定并初始化了处理类

![image-20220213100614812](https://cdn.jsdelivr.net/gh/jianhaojiang/PicGoBed/img/image-20220213100614812.png)

Proxy.newProxyInstance：返回了指定接口的代理类的指定调用处理程序的代理实例

![image-20220213100738730](https://cdn.jsdelivr.net/gh/jianhaojiang/PicGoBed/img/image-20220213100738730.png)

当客户端方法调用的时候，实际将进入代理实例的 invoke 方法，传入代理对象、方法名和参数。invoke 方法的作用就是将请求封装好，发送请求并返回数据【返回的数据使用 json 进行反序列化】

![image-20220213101749426](https://cdn.jsdelivr.net/gh/jianhaojiang/PicGoBed/img/image-20220213101749426.png)

发送请求的 post 方法【将个性化的 request 序列化后发送请求】

![image-20220213102055097](https://cdn.jsdelivr.net/gh/jianhaojiang/PicGoBed/img/image-20220213102055097.png)



### 二、服务端

服务端是一个 springboot 工程，启动后监听 8080 端口

![image-20220213102845848](https://cdn.jsdelivr.net/gh/jianhaojiang/PicGoBed/img/image-20220213102845848.png)

在其主函数，将所有请求转换成自定义的 request ，使用 invoker 的 invoke 方法进行处理。

![image-20220213102948482](https://cdn.jsdelivr.net/gh/jianhaojiang/PicGoBed/img/image-20220213102948482.png)

invoke 方法对 request 进行解析，执行需要执行的方法，将结果封装成个性化的 Response 进行返回

![image-20220213104011457](https://cdn.jsdelivr.net/gh/jianhaojiang/PicGoBed/img/image-20220213104011457.png)



### 三、自定义 RPC 核心包

![image-20220213104723461](https://cdn.jsdelivr.net/gh/jianhaojiang/PicGoBed/img/image-20220213104723461.png)

**RpcfxRequest :** 自定义的 request 请求 ![image-20220213105505694](https://cdn.jsdelivr.net/gh/jianhaojiang/PicGoBed/img/image-20220213105505694.png)

**RpcfxResponse :**自定义的 response 请求

![image-20220213105604821](https://cdn.jsdelivr.net/gh/jianhaojiang/PicGoBed/img/image-20220213105604821.png)

**Rpcfx :** 客户端进行使用，负责封装自定义 request ，序列化之后发送请求，将返回的结果反序列化，返回给程序调用。

**RpcfxInvoker ：**负责服务端使用，将自定义的 request 进行解析，反射调用方法，将方法执行结果序列号后封装返回 Response。

