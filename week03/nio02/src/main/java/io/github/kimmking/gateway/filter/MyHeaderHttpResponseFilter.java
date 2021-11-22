package io.github.kimmking.gateway.filter;

import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.util.internal.StringUtil;

/**
 * 自定义response过滤器,根据respone的body 设置返回respone的kk值
 */
public class MyHeaderHttpResponseFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        String body = response.content().toString(io.netty.util.CharsetUtil.UTF_8);
        if (!StringUtil.isNullOrEmpty(body) && body.contains("蒋建毫")){
            response.headers().set("kk", "hello jjh");
        }else {
            response.headers().set("kk", "java-1-nio");
        }
    }
}
