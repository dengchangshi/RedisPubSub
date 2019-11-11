package com.redispublisher.pub.service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author: dengcs
 * @Date: 2019/11/8 15:56
 * Comment:建立一个Subscriber (订阅者)
 */
public class Subscriber extends Thread{

    //jedis连接池
    private final JedisPool jedisPool;

    private final SubscriberListener subscriberListener;

    private String channelName;

    public Subscriber(JedisPool jedisPool, SubscriberListener subscriberListener, String channelName) {
        super();
        this.jedisPool = jedisPool;
        this.subscriberListener = subscriberListener;
        this.channelName = channelName;
    }

    @Override
    public void run() {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.subscribe(subscriberListener,channelName);// 通过jedis.subscribe()方法去订阅，入参是1.订阅者、2.频道名称
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(String.format("频道订阅失败：%s",e));
        } finally {
            if (null != jedis)
                jedis.close();
        }
    }

}
