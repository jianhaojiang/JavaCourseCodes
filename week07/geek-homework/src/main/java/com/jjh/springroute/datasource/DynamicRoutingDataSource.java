package com.jjh.springroute.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @program: geek-homework
 * @description 动态切换数据源01
 * @author: jjh
 * @create: 2021-12-19 23:19
 **/
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {


    @Override
    protected Object determineCurrentLookupKey() {
        return HandlerDataSource.getDataSource();
    }

}
