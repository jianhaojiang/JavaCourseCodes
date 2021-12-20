package com.jjh.springroute;

import java.lang.annotation.*;

/**
 * @program: geek-homework-week07
 * @description 自定义注解，用于选择数据源
 * @author: jjh
 * @create: 2021-12-20 21:08
 **/
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicSwitchDataSource {
    String dataSource() default "";
}
