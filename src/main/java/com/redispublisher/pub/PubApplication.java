package com.redispublisher.pub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties
public class PubApplication {
    public static void main(String[] args) {
        SpringApplication.run(PubApplication.class, args);
    }

}
