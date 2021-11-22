package io.github.kimmking.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.internal.StringUtil;

/**
 * 自定义request过滤器，请求路由如果包括 jjh 则设置header
 */
public class MyHeaderHttpRequestFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String uri =  fullRequest.uri();
        if (!StringUtil.isNullOrEmpty(uri) && uri.contains("jjh")){
            fullRequest.headers().set("mao", "hello jjh");
        }else {
            fullRequest.headers().set("mao", "hello netty");
        }
    }
}
