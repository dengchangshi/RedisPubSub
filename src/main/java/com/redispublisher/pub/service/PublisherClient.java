package com.redispublisher.pub.service;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: dengcs
 * @Date: 2019/11/8 14:57
 * Comment:发布者客户端
 */
public class PublisherClient {

    private static final String CON_CHANNEL_NAME = "发布者01";
    public static void main(String[] args) throws InterruptedException {

        //创建连接池
        JedisPool jedisPool = new JedisPool("47.112.35.254");
        //创建线程池，并设定线程数量
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //创建一个发布者
        Publisher publisher = new Publisher(jedisPool,CON_CHANNEL_NAME);
        executorService.submit(publisher);
        executorService.shutdown();
        executorService.awaitTermination(600, TimeUnit.SECONDS);

    }


}

