package com.sandata.lab.rules.vv.imports.data.impl;

import com.sandata.lab.redis.connection.jedis.SandataJedisConnectionFactory;
import com.sandata.lab.redis.services.impl.SandataRedisCacheService;
import com.sandata.lab.rules.vv.imports.BaseTestSupport;
import com.sandata.lab.rules.vv.imports.model.EVVCall;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by khangle on 09/23/2016.
 */
public class EVVRedisCallStoreTest extends BaseTestSupport {
    private SandataJedisConnectionFactory jedisConnectionFactory;
    private RedisTemplate redisTemplate;
    private SandataRedisCacheService redisCacheService;
    private EVVRedisCallStore evvCallStore;

    @Test
    public void testAdd1000Calls() throws Exception {
        long start = System.currentTimeMillis();
        List<EVVCall> evvCallList = prepareTestEvvCalls(1000);
        evvCallStore.storeCalls(evvCallList);
        System.out.println("Completed inserting in " + (System.currentTimeMillis() - start) + "ms");

        // Clean up data
        for (EVVCall call : evvCallList) {
            evvCallStore.deleteCallFromStore(call);
        }
        System.out.println("Completed in " + (System.currentTimeMillis() - start) + "ms");
    }


    @Test
    public void testAddGetDeleteCalls() throws Exception {
        // Store call
        List<EVVCall> evvCallList = prepareTestEvvCalls(1);
        evvCallStore.storeCalls(evvCallList);

        // Get calls from store
        EVVCall retrievedCall = evvCallStore.getCallFromStore(evvCallList.get(0));
        Assert.assertNotNull(retrievedCall);

        // Get unprocessed calls
        List<EVVCall> allCalls = evvCallStore.getUnprocessedCalls();
        Assert.assertNotNull(allCalls);
        System.out.println(allCalls.size());
        Assert.assertTrue(allCalls.size() >= 1);

        // Try to get 1 unprocessed call
        EVVCall unprocessedCall = evvCallStore.getCallFromStore(allCalls.get(0));
        Assert.assertNotNull(unprocessedCall);

        // Delete call
        for (EVVCall call : allCalls) {
            evvCallStore.deleteCallFromStore(call);
        }

        allCalls = evvCallStore.getUnprocessedCalls();
        Assert.assertNotNull(allCalls);
        Assert.assertTrue(allCalls.size() == 0);
    }

    @Override
    protected void onSetup() throws IOException {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(10);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(4);
        jedisPoolConfig.setTestOnBorrow(true);

        jedisConnectionFactory = new SandataJedisConnectionFactory();
        jedisConnectionFactory.setHostName("127.0.0.1");
        jedisConnectionFactory.setPort(6379);
        jedisConnectionFactory.setDatabase(5);
        jedisConnectionFactory.setPassword("sandatalab");
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        jedisConnectionFactory.afterPropertiesSet();

        redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        redisTemplate.afterPropertiesSet();

        redisCacheService = new SandataRedisCacheService("SandataVVImportCalls", 600, redisTemplate);

        evvCallStore = new EVVRedisCallStore();
        evvCallStore.setSandataRedisService(redisCacheService);
    }

    private List<EVVCall> prepareTestEvvCalls(int numOfCall) {
        List<EVVCall> evvCalls = new ArrayList<>();

        for (int i = 0; i < numOfCall; i++) {
            EVVCall call = new EVVCall();
            call.setDnis(String.valueOf(new Random().nextInt()));
            call.setCallDtimeUTC(new DateTime());
            call.setAni("1234567890");
            call.setSid("sid");

            evvCalls.add(call);
        }

        return evvCalls;
    }
}
