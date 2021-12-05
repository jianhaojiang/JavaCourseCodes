package com.jjh.week05.work02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: geek-homework
 * @description
 * @author: jjh
 * @create: 2021-12-05 00:42
 **/
@Configuration
public class StudentBeanConfig {

    @Bean(name = "student3")
    public Student getStudent3() {
        return new Student(333, "Student3");
    }

}