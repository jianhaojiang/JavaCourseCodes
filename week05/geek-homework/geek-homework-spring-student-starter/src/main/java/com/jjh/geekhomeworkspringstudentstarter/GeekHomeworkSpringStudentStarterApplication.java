package com.jjh.geekhomeworkspringstudentstarter;

import com.jjh.work08.ISchool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GeekHomeworkSpringStudentStarterApplication {

    /**
     * 测试 starter 启动自动配置的bean
     */
    @Autowired
    ISchool school;


    public static void main(String[] args) {
        SpringApplication.run(GeekHomeworkSpringStudentStarterApplication.class, args);
    }

    @Bean
    public void printInfo(){
        System.out.println("===============================");
        //验证现在school中自动注入的bean，为starter自动装配的bean
        System.out.println(school);
        System.out.println("===============================");
    }

}
