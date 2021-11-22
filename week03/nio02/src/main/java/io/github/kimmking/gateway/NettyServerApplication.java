package io.github.kimmking.gateway;


import io.github.kimmking.gateway.inbound.HttpInboundServer;

import java.util.Arrays;

/**
 * 1.HttpInboundServer 是入口，通过HttpInboundInitializer初始化，HttpInboundHandler作为处理类
 * 2.HttpInboundHandler 定义了HttpOutboundHandler处理类和request过滤器HttpRequestFilter，调用HttpOutboundHandler的handle方法，传递了request、ChannelHandlerContext对象和过滤器对象作为参数
 * 3.HttpOutboundHandler的handle方法，做了以下几件事情：
 *      1)调用路由获取到要请求的服务器
 *      2)调用requestFilter
 *      3)调用线程池，使用线程处理具体的业务
 * 4.业务线程 使用httpclient 请求服务器拿到response,最后准备返回，返回前调用了responseFilter，最后将数据写入 ChannelHandlerContext
 */
public class NettyServerApplication {
    
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "3.0.0";
    
    public static void main(String[] args) {

        String proxyPort = System.getProperty("proxyPort","8888");

        // 这是之前的单个后端url的例子
//        String proxyServer = System.getProperty("proxyServer","http://localhost:8088");
//          //  http://localhost:8888/api/hello  ==> gateway API
//          //  http://localhost:8088/api/hello  ==> backend service
        // java -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar  #作为后端服务


        // 这是多个后端url走随机路由的例子
        String proxyServers = System.getProperty("proxyServers","http://localhost:8801,http://localhost:8802");
        int port = Integer.parseInt(proxyPort);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
        HttpInboundServer server = new HttpInboundServer(port, Arrays.asList(proxyServers.split(",")));
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + server.toString());
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
