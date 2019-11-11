package com.redispublisher.pub;

import com.redispublisher.pub.config.RedisCacheConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@SpringBootTest
class PubApplicationTests {

    @Autowired
    private RedisCacheConfiguration redisCacheConfiguration;
    @Test
    void contextLoads() {
        JedisPool jedisPool = redisCacheConfiguration.redisPoolFactory();
        Jedis jedis = jedisPool.getResource();//从连接池中获取jedis
        System.out.println(jedis);
    }
}
