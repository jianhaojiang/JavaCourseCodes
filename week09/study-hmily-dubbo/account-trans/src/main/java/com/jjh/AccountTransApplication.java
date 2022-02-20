package com.jjh;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 学习模仿优秀作业
 * 优秀作业：https://gitee.com/lahuan/java-course-codes/tree/master/week_09/week09_question07_dubbo_hmily
 * 1.表设计
 * 2.api子包：包括实体类、mapper、service接口
 * 3.account服务子包 提供账户余额操作相关方法
 * 主工程调用转账接口，接口内执行TCC分布式事务，try执行余额冻结方法，根据执行情况调用confirm或cancel方法
 * confirm: 进行余额的操作并删除对应的冻结表记录
 */
@EnableDubbo
@SpringBootApplication
public class AccountTransApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountTransApplication.class, args);
    }

}
