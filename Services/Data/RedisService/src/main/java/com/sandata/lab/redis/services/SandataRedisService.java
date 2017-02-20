package com.sandata.lab.redis.services;

import com.sandata.lab.redis.connection.SandataRedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Abstract class that defines basic operations: PUT, GET, DELETE for working with Redis
 */
public abstract class SandataRedisService {

    protected RedisTemplate redisTemplate;

    protected String keyPrefix;

    /**
     * Constructor
     *
     * @param keyPrefix
     * @param redisTemplate
     */
    public SandataRedisService(String keyPrefix, RedisTemplate redisTemplate) {
        this.keyPrefix = keyPrefix;
        this.redisTemplate = redisTemplate;
        this.redisTemplate.setKeySerializer(new StringRedisSerializer());
    }

    /**
     * Add a pair of key-value to Redis
     *
     * @param key
     * @param value
     */
    public abstract void put(String key, Object value);

    /**
     * Get value of a key
     *
     * @param key
     * @return
     */
    public abstract Object get(String key);

    /**
     * Delete a key and its value
     *
     * @param key
     */
    public abstract void delete(String key);

    /**
     * Find key set by pattern. See http://redis.io/commands/keys for more information.
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(buildKeyString(pattern));
    }

    /**
     * Build a key to be used in Redis by combining keyPrefix and specified key
     *
     * @param key
     * @return
     */
    public String buildKeyString(String key) {
        return keyPrefix + "." + key;
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}
