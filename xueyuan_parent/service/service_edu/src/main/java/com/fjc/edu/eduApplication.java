package com.fjc.edu;

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
 * @create: 2021-01-25 21:42
 **/
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@ComponentScan(basePackages = {"com.fjc"})/*从外向里扫描 加上要用到的包名设置包的扫描规则*/
public class eduApplication {
    public static void main(String[] args) {
        SpringApplication.run(eduApplication.class,args);
    }
}
