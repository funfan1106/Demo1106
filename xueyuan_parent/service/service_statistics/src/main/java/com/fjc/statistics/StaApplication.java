package com.fjc.statistics;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: guli_parent
 * @description: 启动类
 * @author: Mr.Fan
 * @create: 2021-03-29 22:42
 **/
@SpringBootApplication
@MapperScan("com.fjc.statistics.mapper")
@ComponentScan("com.fjc")
@EnableDiscoveryClient
@EnableFeignClients
//@EnableScheduling
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class, args);
    }
}