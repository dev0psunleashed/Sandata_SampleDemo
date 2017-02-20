package com.sandata.lab.redis.connection;

import org.springframework.data.redis.connection.RedisSentinelConfiguration;

/**
 * Created by khangle on 01/11/2017.
 */
public class SandataRedisSentinelConfiguration extends RedisSentinelConfiguration {

    /**
     * Constructor that takes in master node name, list of sentinel in format: host1:port,host2:port,host3:port...
     *
     * @param master
     * @param sentinelList
     */
    public SandataRedisSentinelConfiguration(String master, String sentinelList) {
        super();
        this.master(master);
        String[] sentinelArray = sentinelList.split(",");
        String [] hostPort = null;
        for (String sentinel : sentinelArray) {
            hostPort = sentinel.split(":");
            this.sentinel(hostPort[0], Integer.parseInt(hostPort[1]));
        }
    }
}
