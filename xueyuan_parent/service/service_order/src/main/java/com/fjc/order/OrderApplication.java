package com.fjc.order;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: guli_parent
 * @description: 启动类
 * @author: Mr.Fan
 * @create: 2021-03-27 16:25
 **/
@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.fjc"})
@MapperScan("com.fjc.order.mapper")
public class OrderApplication {
    public static  void main(String[] args){
        SpringApplication.run(OrderApplication.class,args);
    }
}
