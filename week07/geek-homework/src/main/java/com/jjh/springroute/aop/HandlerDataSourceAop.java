package com.jjh.springroute.aop;


import com.jjh.springroute.annotaction.DynamicSwitchDataSource;
import com.jjh.springroute.datasource.HandlerDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @program: geek-homework-week07
 * @description 使用AOP拦截特定的注解去动态的切换数据源
 * @author: jjh
 * @create: 2021-12-20 21:10
 **/
@Aspect
@Slf4j
@Component
@Order(1)
public class HandlerDataSourceAop {
    //@within在类上设置
    //@annotation在方法上进行设置
    @Pointcut("@within(com.jjh.springroute.annotaction.DynamicSwitchDataSource)||" +
            "@annotation(com.jjh.springroute.annotaction.DynamicSwitchDataSource)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //获取方法上的注解
        DynamicSwitchDataSource annotationClass = method.getAnnotation(DynamicSwitchDataSource.class);
        if (annotationClass == null) {
            //获取类上面的注解
            annotationClass = joinPoint.getTarget().getClass().getAnnotation(DynamicSwitchDataSource.class);
            if (annotationClass == null){
                return;
            }
        }
        //获取注解上的数据源的值的信息
        String dataSourceKey = annotationClass.dataSource();
        if (dataSourceKey != null) {
            //给当前的执行SQL的操作设置特殊的数据源的信息
            HandlerDataSource.putDataSource(dataSourceKey);
        }
        log.info("AOP动态切换数据源，className" + joinPoint.getTarget().getClass().getName() + "methodName" + method.getName() + ";dataSourceKey:" + dataSourceKey == "" ? "默认数据源" : dataSourceKey);
    }

    @After("pointcut()")
    public void after(JoinPoint point) {
        //清理掉当前设置的数据源，让默认的数据源不受影响
        HandlerDataSource.clear();
    }

}

