package com.jjh.shardingsphere;


import com.jjh.shardingsphere.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.Resource;

@SpringBootApplication
public class ShardingSphereApplication {

    @Resource
    IUserService userService;


    public static void main(String[] args) {
        SpringApplication.run(ShardingSphereApplication.class, args);
    }

    @Bean
    public void printInfo(){
        System.out.println("===============================");
        //查询从库的数据
        userService.findUser("jjh");
        //将数据写入主库
        userService.insertUser("jjh");
        System.out.println("===============================");
    }

}
