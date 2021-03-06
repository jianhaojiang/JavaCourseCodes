package com.jjh.springroute;

import com.jjh.springroute.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringRouteApplication {

    @Autowired
    IUserService userService;


    public static void main(String[] args) {
        SpringApplication.run(SpringRouteApplication.class, args);
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
