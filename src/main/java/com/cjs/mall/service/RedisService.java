package com.cjs.mall.service;

/**
 * @description:
 * @author: cuijunsheng
 * @date: 2020-01-14 13:28
 **/
public interface RedisService {

    /**
     * 设置key
     *
     * @param key
     * @param value
     */
    void set(String key, String value);

    /**
     * 获取key
     *
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 设置过期时间
     *
     * @param key
     * @param expireTime
     * @return
     */
    boolean expire(String key, long expireTime);

    /**
     * 删除key
     *
     * @param key
     */
    void remove(String key);

    /**
     * 增量
     *
     * @param key
     * @param delta
     * @return
     */
    Long increment(String key, long delta);
}
