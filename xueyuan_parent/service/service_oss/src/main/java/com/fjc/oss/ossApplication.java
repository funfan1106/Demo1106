package com.fjc.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @program: guli_parent
 * @description: oss的启动类
 * @author: Mr.Fan
 * @create: 2021-02-23 18:14
 **/

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.fjc"})
public class ossApplication {
    public static void main(String[] args) {
        SpringApplication.run(ossApplication.class, args);
    }
}
