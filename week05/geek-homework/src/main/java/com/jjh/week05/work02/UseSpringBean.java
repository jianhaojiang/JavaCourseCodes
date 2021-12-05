package com.jjh.week05.work02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class UseSpringBean {
    
    public static void main(String[] args) {
        
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        //@Component 注解
        Student student = (Student) context.getBean("student");
        System.out.println(student.toString());
//        student.print();

        //XML配置
        Student student2 = (Student) context.getBean("student2");
        System.out.println(student2.toString());

        //@Configuration @Bean 获取
        Student student3 = (Student) context.getBean("student3");
        System.out.println(student3.toString());

    }
}
