package com.sandata.lab.redis.connection;


import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * Interface that is exposed as a service in OSGI container
 */
public interface SandataRedisConnectionFactory extends RedisConnectionFactory {

}
