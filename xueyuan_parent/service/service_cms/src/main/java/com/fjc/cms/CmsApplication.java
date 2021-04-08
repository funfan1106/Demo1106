package com.fjc.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: guli_parent
 * @description: 启动类
 * @author: Mr.Fan
 * @create: 2021-03-16 18:36
 **/

@SpringBootApplication
@ComponentScan({"com.fjc"}) //指定扫描位置
@MapperScan("com.fjc.cms.mapper")
@EnableDiscoveryClient
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}