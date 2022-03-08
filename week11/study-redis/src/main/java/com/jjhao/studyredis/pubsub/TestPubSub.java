package com.jjhao.studyredis.pubsub;

/**
 * @program: study-redis
 * @description
 * @author: jjh
 * @create: 2022-03-08 16:10
 **/
public class TestPubSub {

    public static void main(String[] args) throws InterruptedException {
        //监听通道
        new RedisSubscribe("127.0.0.1", 6379).subscribeMessage("jjh");

        Thread.sleep(3000);

        //向通道发布一条信息
        new RedisPublish("127.0.0.1", 6379)
                .publishMessage("jjh", "hello");


    }


}
