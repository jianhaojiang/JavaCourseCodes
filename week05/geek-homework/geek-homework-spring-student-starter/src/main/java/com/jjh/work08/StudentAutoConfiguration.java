package com.jjh.work08;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: geek-homework
 * @description
 * @author: jjh
 * @create: 2021-12-05 15:14
 **/
@Configuration
@ComponentScan("com.jjh.work08")
public class StudentAutoConfiguration implements EnvironmentAware {


    /**
     * starter 自动装配 student
     * @return
     */
    @Bean(name = "student100")
    public Student getStudent100() {
        Student student100 = Student.create();
        student100.setId(100);
        student100.setName("student100");
        return student100;
    }

    /**
     * starter 自动装配 Klass
     * @return
     */
    @Bean(name = "class22")
    public Klass getKlass() {
        Klass class1 = new Klass();
        List<Student> students = new ArrayList<>();
        students.add(Student.create());
        students.add(Student.create());
        class1.setStudents(students);
        return class1;
    }


    @Override
    public void setEnvironment(Environment environment) {
    }


}
