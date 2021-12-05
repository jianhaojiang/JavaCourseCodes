package com.jjh.week05.work02;


import lombok.Data;
import lombok.ToString;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Data
@ToString
@Component
public class Student implements Serializable, ApplicationContextAware {

    private int id;
    private String name;

    private ApplicationContext applicationContext;

    public Student() {
        this.id = 111;
        this.name = "Student1";
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void print() {
        System.out.println("   context.getBeanDefinitionNames() ===>> "
                + String.join(",", applicationContext.getBeanDefinitionNames()));

    }

}
