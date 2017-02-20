package com.sandata.lab.redis.services.impl;

import com.sandata.lab.redis.BaseTestSupport;
import com.sandata.lab.redis.connection.SandataRedisSentinelConfiguration;
import com.sandata.lab.redis.connection.jedis.SandataJedisConnectionFactory;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SandataRedisCacheServiceTest extends BaseTestSupport {

    private SandataJedisConnectionFactory jedisConnectionFactory;

    private RedisTemplate redisTemplate;

    private SandataRedisCacheService redisCacheService;

    @Test
    public void test_operations() throws Exception {
        String keyName = "MyCacheKey";

        redisCacheService.put(keyName, "Hello world");

        String value = (String)redisCacheService.get(keyName);
        Assert.assertEquals("Hello world", value);

        redisCacheService.delete(keyName);

        value = (String)redisCacheService.get("MyCacheKey");
        Assert.assertNull(value);

    }

    @Test
    public void test_get_key_reset_time_to_live() throws Exception {
        String keyName = "UpdateKey";

        redisCacheService.put(keyName, new Integer(123));
        Thread.sleep(10000);

        Assert.assertEquals(123, redisCacheService.get(keyName));
        Assert.assertTrue(redisCacheService.getRedisTemplate().getExpire(redisCacheService.buildKeyString(keyName), TimeUnit.SECONDS) > 295);

    }

    @Test
    public void test_get_non_existing_key() throws Exception {
        String keyName = "NewKey_" + System.currentTimeMillis();
        Object value = redisCacheService.get(keyName);
        Assert.assertNull(value);
    }

    @Test
    public void test_get_keySet() throws Exception {
        redisCacheService.put("Cache.Key_1", new Exception("Test exception"));
        redisCacheService.put("Cache.Key_2", "Key_2 value");
        redisCacheService.put("Cache.Key_3", "Key_3 value");

        Set<String> keyset = redisCacheService.keys("Cache.*");
        Assert.assertTrue(keyset.contains("SandataRedisTest.Cache.Key_1"));
        Assert.assertTrue(keyset.contains("SandataRedisTest.Cache.Key_2"));
        Assert.assertTrue(keyset.contains("SandataRedisTest.Cache.Key_3"));
    }

    @Override
    protected void onSetup() throws Exception {
//        RedisSentinelConfiguration redisSentinelConfiguration =
//                new RedisSentinelConfiguration()
//                        .master("rdmaster")
//                        .sentinel("rd-lab-ses01", 26300)
//                        .sentinel("rd-lab-ses02", 26300)
//                        .sentinel("rd-lab-ses03", 26300);

        SandataRedisSentinelConfiguration sandataRedisSentinelConfiguration =
                new SandataRedisSentinelConfiguration(
                        "rdmaster",
                        "rd-lab-ses01:26300,rd-lab-ses02:26300,rd-lab-ses03:26300");

        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(4);
        jedisPoolConfig.setTestOnBorrow(true);

        jedisConnectionFactory = new SandataJedisConnectionFactory(sandataRedisSentinelConfiguration, jedisPoolConfig);
        jedisConnectionFactory.setDatabase(4);
        jedisConnectionFactory.setPassword("sandatalab");
        jedisConnectionFactory.setTimeout(5000);
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.afterPropertiesSet();

        redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.afterPropertiesSet();

        redisCacheService = new SandataRedisCacheService("SandataRedisTest", redisTemplate);
    }
}