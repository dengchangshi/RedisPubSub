package com.redispublisher.pub.service;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author: dengcs
 * @Date: 2019/11/8 14:28
 * Comment:建立一个Publisher (发布者)
 */


public class Publisher extends Thread {
    private final JedisPool jedisPool;
    private String chanelName;

    public Publisher(JedisPool jedisPool, String chanelName) {
        this.jedisPool = jedisPool;
        this.chanelName = chanelName;
        System.out.println("【发布者\""+chanelName+"\"初始化成功】");
        System.out.println("请输入要发布的消息:");
    }

    @Override
    public void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));//用来从键盘接受一行输入的
        Jedis jedis = jedisPool.getResource();
        while (true) {
            String line = null;
            try {
                line = reader.readLine();
                if (!"quit".equals(line)) {
                    System.out.println(chanelName+"发布消息成功");
                    jedis.publish(chanelName, line);
                } else {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}


