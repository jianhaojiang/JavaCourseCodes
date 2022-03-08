package com.jjhao.studyredis;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * @program: study-redis
 * @description
 * @author: jjh
 * @create: 2022-03-07 17:26
 **/
public class RedisLock {

    private final JedisPool jedisPool;

    public RedisLock(String host, int port) {
        this.jedisPool = new JedisPool(host, port);
    }

    /**
     * 分布式锁
     * SET dlock my_random_value NX PX 30000
     * 首先设置一个key的值，然后指定当不存在的时候才塞这个值（NX），
     * 然后避免一直占用锁，设置30秒超时失效（PX 30000 == ex 30）;
     * @param lockKey
     * @param randomValue
     * @param seconds
     * @return
     */
    public boolean redisLock(String lockKey, String randomValue, long seconds){
        try (Jedis jedis = jedisPool.getResource()) {
            SetParams params = new SetParams().ex(seconds).nx();
            return "OK".equals(jedis.set(lockKey, randomValue, params));
        }
    }

    /**
     * 释放锁
     *  1.先检测当前的锁是不是自己的
     *  2.释放锁
     *  两步操作分别是原子性的，但用程序执行存在并发问题
     *  借用redis lua 脚本方式，在redis的单线程中执行，避免并发问题
     * @param lockKey
     * @param randomValue
     * @return
     */
    public boolean releaseLock(String lockKey, String randomValue){
        String luaScript =
                "if redis.call('get',KEYS[1]) == ARGV[1] then " +
                "return redis.call('del',KEYS[1]) else return 0 end ";
        try (Jedis jedis = jedisPool.getResource()) {
            return jedis.eval(luaScript, Collections.singletonList(lockKey), Collections.singletonList(randomValue)).equals(1L);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        RedisLock redisLock = new RedisLock("127.0.0.1", 6379);
        boolean flag = redisLock.redisLock("jjh", "hh", 30);
        System.out.println("锁住key jjh, 值是hh, 结果："+ flag);
        boolean flag2 = redisLock.redisLock("jjh", "heihei", 30);
        System.out.println("锁住key jjh, 值是heihei, 结果："+ flag2);
        boolean flag3 = redisLock.releaseLock("jjh", "hh");
        System.out.println("解锁key jjh, 值是hh, 结果："+ flag3);
        boolean flag4 = redisLock.redisLock("jjh", "heihei", 30);
        System.out.println("锁住key jjh, 值是heihei, 结果："+ flag4);

        Thread.sleep(300000);
    }




}
