package com.cjs.mall.service.impl;

import com.cjs.mall.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020-01-14 13:35
 **/
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void set(String key, String value) {

        stringRedisTemplate.opsForValue().set(key,value);
    }

    @Override
    public String get(String key) {

        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean expire(String key, long expireTime) {

        return stringRedisTemplate.expire(key,expireTime, TimeUnit.SECONDS);
    }

    @Override
    public void remove(String key) {

        stringRedisTemplate.delete(key);
    }

    @Override
    public Long increment(String key, long delta) {

        return stringRedisTemplate.opsForValue().increment(key,delta);
    }
}
