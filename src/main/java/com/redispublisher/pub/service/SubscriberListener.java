package com.redispublisher.pub.service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

/**
 * @Author: dengcs
 * @Date: 2019/11/8 14:31
 * Comment:监听器
 */
public class SubscriberListener extends JedisPubSub {
    private String subName;
    public SubscriberListener() {
    }

    public SubscriberListener(String subName) {
        this.subName = subName;
    }

    @Override
    public void onMessage(String channel, String message) {       //收到消息会调用
        System.out.println(String.format("receive redis published message, channel %s, message %s", channel, message));
    }
    @Override
    public void onSubscribe(String channel, int subscribedChannels) {    //订阅了频道会调用
        System.out.println(String.format("subscribe redis channel success, channel %s, subscribedChannels %d",
                channel, subscribedChannels));
    }
    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {   //取消订阅 会调用
        System.out.println(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d",
                channel, subscribedChannels));

    }
}
