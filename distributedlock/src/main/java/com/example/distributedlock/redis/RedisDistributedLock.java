package com.example.distributedlock.redis;

import org.redisson.Redisson;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author yanglin
 * @date 2020/7/21 16:39
 */
public class RedisDistributedLock {

    @Resource
    public RedisTemplate redisTemplate;

    @Resource
    public Redisson redisson;

    public void lock(){

    }
}
