package com.jjhao.studyredis.pubsub;

import lombok.extern.java.Log;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @program: study-redis
 * @description
 * @author: jjh
 * @create: 2022-03-08 15:53
 **/
public class RedisPublish {

    private final JedisPool jedisPool;


    public RedisPublish(String host, int port) {
        this.jedisPool = new JedisPool(host, port);
    }

    public void publishMessage(String channelName, String message){
        try(Jedis jedis = jedisPool.getResource()){
            System.out.println("向通道【"+channelName+"】发送了一条消息："+message);
            jedis.publish(channelName, message);
        }
    }

}
