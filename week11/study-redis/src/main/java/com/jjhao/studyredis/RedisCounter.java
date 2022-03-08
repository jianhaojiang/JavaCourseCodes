package com.jjhao.studyredis;

import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @program: study-redis
 * @description
 * @author: jjh
 * @create: 2022-03-07 17:00
 **/
public class RedisCounter {

    private final JedisPool jedisPool;

    public RedisCounter(String host, int port) {
        this.jedisPool = new JedisPool(host, port);
    }

    //增加
    public void increase(String key, long count){
        try(Jedis jedis = jedisPool.getResource()){
            jedis.incrBy(key, count);
//            System.out.println(jedis.set("11", "ddd"));
        }
    }

    //获取
    public Long count(String key) {
        try (Jedis jedis = jedisPool.getResource()) {
            String s = jedis.get(key);
            if (StringUtils.isEmpty(s)){
                return null;
            }
            return Long.valueOf(jedis.get(key));
        }
    }


    public static void main(String[] args) {
        String key = "test_counter";
        Long count;
        RedisCounter counter = new RedisCounter("127.0.0.1", 6379);
        count = counter.count(key);
        System.out.println("最初:"+count);
        counter.increase(key, 1);
        counter.increase(key, 2);
        count = counter.count(key);
        System.out.println("累加:"+count);

        counter.increase(key, -1);
        counter.increase(key, -1);
        counter.increase(key, -1);
        count = counter.count(key);
        System.out.println("累减:"+count);
    }



}
