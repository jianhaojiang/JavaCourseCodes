package com.jjh;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: study-hmily-dubbo
 * @description
 * @author: jjh
 * @create: 2022-02-20 11:08
 **/
@EnableDubbo
@SpringBootApplication
public class AccountUsdApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountUsdApplication.class, args);
    }

}
