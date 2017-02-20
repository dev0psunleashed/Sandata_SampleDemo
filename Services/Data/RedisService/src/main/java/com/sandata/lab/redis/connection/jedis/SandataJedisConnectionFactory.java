package com.sandata.lab.redis.connection.jedis;

import com.sandata.lab.redis.connection.SandataRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Concrete implementation of <code>SandataRedisConnectionFactory</code> and extends from <code>JedisConnectionFactory</code>
 */
public class SandataJedisConnectionFactory extends JedisConnectionFactory implements SandataRedisConnectionFactory {

    /**
     * Default constructor
     */
    public SandataJedisConnectionFactory() {
        super();
    }

    public SandataJedisConnectionFactory(RedisSentinelConfiguration sentinelConfig) {
        super(sentinelConfig);
    }

    /**
     * Constructor
     *
     * @param sentinelConfig
     * @param poolConfig
     */
    public SandataJedisConnectionFactory(RedisSentinelConfiguration sentinelConfig, JedisPoolConfig poolConfig) {
        super(sentinelConfig, poolConfig);
    }
}
