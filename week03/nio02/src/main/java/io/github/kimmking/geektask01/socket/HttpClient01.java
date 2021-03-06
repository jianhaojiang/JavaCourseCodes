package io.github.kimmking.geektask01.socket;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @program: jvm-week1
 * @description
 * @author: jjh
 * @create: 2021-11-14 19:42
 **/
@SpringBootApplication
@RestController
@ComponentScan("io.github.kimmking.geektask01")
public class HttpClient01 {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpClient01.class);

    /**
     * 使用HttpClient请求
     *
     * @return
     */
    @GetMapping("/getHttpClientOld")
    public Object getHttpClientOld() {
        String result = "";
        try {
            CloseableHttpClient build = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet("http://localhost:8801");
            //请求数据
            CloseableHttpResponse response = build.execute(httpGet);
            result = EntityUtils.toString(response.getEntity());
            //打印返回
            LOGGER.info("响应状态为:{}", response.getStatusLine());
            LOGGER.info("请求结果为:{}", result);
            //关闭连接
            response.close();
            build.close();
        } catch (IOException e) {
            LOGGER.error("IO异常：", e);
        }
        return result;
    }

    /**
     * 使用HttpClient请求
     *
     * @return
     */
    @GetMapping("/getHttpClient")
    public Object getHttpClient() {
        String result = "";
        try {
            CloseableHttpClient build = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet("http://localhost:8801");
            //请求数据
            CloseableHttpResponse response = build.execute(httpGet);
            result = EntityUtils.toString(response.getEntity());
            //打印返回
            LOGGER.info("响应状态为:{}", response.getStatusLine());
            LOGGER.info("请求结果为:{}", result);
            //关闭连接
            response.close();
            build.close();
        } catch (IOException e) {
            LOGGER.error("IO异常：", e);
        }
        return result;
    }

    public static void main(String[] args) {
        SpringApplication.run(HttpClient01.class, args);
    }


}
