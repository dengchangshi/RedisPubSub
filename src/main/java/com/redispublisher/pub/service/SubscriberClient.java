package com.redispublisher.pub.service;

import com.redispublisher.pub.config.RedisCacheConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: dengcs
 * @Date: 2019/11/8 16:04
 * Comment:订阅者客户端
 */
public class SubscriberClient {

    private static final String CON_CHANNEL_NAME = "发布者01";

    public static void main(String[] args) throws InterruptedException {
        //创建redis连接池
        JedisPool jedisPool = new JedisPool("47.112.35.254");
        //创建线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        //创建订阅者
        final SubscriberListener subscriberListener = new SubscriberListener("订阅者一号");
        //订阅频道
        Subscriber subscriber = new Subscriber(jedisPool, subscriberListener, CON_CHANNEL_NAME);
        executorService.submit(subscriber);
        executorService.shutdown();
        executorService.awaitTermination(60, TimeUnit.SECONDS);

        //30s后取消订阅
        Thread.sleep(3000);
        subscriberListener.unsubscribe(CON_CHANNEL_NAME);
    }
}
