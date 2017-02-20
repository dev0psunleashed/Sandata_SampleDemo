package com.sandata.lab.redis.services.impl;

import com.sandata.lab.redis.services.SandataRedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * <p>Concrete implementation of <code>SandataRedisService</code> to provide methods to work with Redis as a cache. Every key has default
 * time to live(TTL) of 300 seconds or TTL can be specified via constructor.</p>
 * <p>When a key is accessed (GET), its TTL will be reset to original value.</p>
 */
public class SandataRedisCacheService extends SandataRedisService {

    private ValueOperations<String, Object> valueOps;

    private long timeToLive;    // in seconds

    /**
     * Constructor with default Time To Live of 300 seconds
     *
     * @param keyPrefix
     */
    public SandataRedisCacheService(String keyPrefix, RedisTemplate redisTemplate) {
        super(keyPrefix, redisTemplate);
        this.valueOps = getRedisTemplate().opsForValue();
        this.timeToLive = 300L;
    }

    /**
     * Construction that takes in Time To Live in seconds
     *
     * @param keyPrefix
     * @param timeToLive
     * @param redisTemplate
     */
    public SandataRedisCacheService(String keyPrefix, long timeToLive, RedisTemplate redisTemplate) {
        this(keyPrefix, redisTemplate);
        this.timeToLive = timeToLive;
    }

    @Override
    public void put(String key, Object value) {
        valueOps.set(buildKeyString(key), value, timeToLive, TimeUnit.SECONDS);
    }

    @Override
    public Object get(String key) {
        Object value = valueOps.get(buildKeyString(key));

        // Reset key's time to live
        this.redisTemplate.expire(buildKeyString(key), timeToLive, TimeUnit.SECONDS);

        return value;
    }

    @Override
    public void delete(String key) {
        getRedisTemplate().delete(buildKeyString(key));
    }
}
