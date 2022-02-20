package com.jjh;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: study-hmily-dubbo
 * @description dubbo服务包
 * 实现人民币转账相关方法，提供service。
 * 两个注解 @EnableDubbo 和 @DubboService
 * 注解 @EnableDubbo：包含多个子主键，主要是DubboComponentScan 默认扫描包路径为 yml 配置的 base-packages
 * 注解 @DubboService：表明当前类实现提供服务
 * @author: jjh
 * @create: 2022-02-20 09:58
 **/
@EnableDubbo
@SpringBootApplication
public class AccountRmbApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountRmbApplication.class, args);
    }

}
