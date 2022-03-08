package com.jjhao.studyredis.pubsub;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * @program: study-redis
 * @description
 * @author: jjh
 * @create: 2022-03-08 15:54
 **/
public class RedisSubscribe {

    private final JedisPool jedisPool;

    public RedisSubscribe(String host, int port) {
        this.jedisPool = new JedisPool(host, port);
    }

    public void subscribeMessage(String channelName){
        new Thread(() -> {
            try(Jedis jedis = jedisPool.getResource()){
                jedis.subscribe(new JedisPubSub() {
                    @Override
                    public void onMessage(String channel, String message) {
                        System.out.println("接收到通道【"+channel+"】的消息："+message);
                    }
                }, channelName);
            }
        }).start();
    }



}
