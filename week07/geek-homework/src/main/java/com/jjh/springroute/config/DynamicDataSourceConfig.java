package com.jjh.springroute.config;

import com.jjh.springroute.datasource.DynamicRoutingDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: geek-homework-week07
 * @description
 * @author: jjh
 * @create: 2021-12-20 22:19
 **/
@Configuration
public class DynamicDataSourceConfig {
    @Value("${hikariCP.driver}")
    private String driver;

    @Value("${hikariCP.url}")
    private String url;

    @Value("${hikariCP2.url}")
    private String url2;

    @Value("${hikariCP.username}")
    private String username;

    @Value("${hikariCP.password}")
    private String password;

    @Value("${hikariCP.cachePrepStmts}")
    private boolean cachePrepStmts;

    @Value("${hikariCP.prepStmtCacheSize}")
    private int prepStmtCacheSize;

    @Value("${hikariCP.prepStmtCacheSqlLimit}")
    private int prepStmtCacheSqlLimit;

    @Bean
    public HikariDataSource master(){
        HikariDataSource hikariDataSource=new HikariDataSource();
        hikariDataSource.setDriverClassName(driver);
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);
        hikariDataSource.addDataSourceProperty("cachePrepStmts",cachePrepStmts);
        hikariDataSource.addDataSourceProperty("prepStmtCacheSize",prepStmtCacheSize);
        hikariDataSource.addDataSourceProperty("prepStmtCacheSqlLimit",prepStmtCacheSqlLimit);
        return hikariDataSource;
    }

    @Bean
    public HikariDataSource slave(){
        HikariDataSource hikariDataSource=new HikariDataSource();
        hikariDataSource.setDriverClassName(driver);
        hikariDataSource.setJdbcUrl(url2);
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);
        hikariDataSource.addDataSourceProperty("cachePrepStmts",cachePrepStmts);
        hikariDataSource.addDataSourceProperty("prepStmtCacheSize",prepStmtCacheSize);
        hikariDataSource.addDataSourceProperty("prepStmtCacheSqlLimit",prepStmtCacheSqlLimit);
        return hikariDataSource;
    }


    @Bean
    @Primary
    public DynamicRoutingDataSource dataSource() {
        DynamicRoutingDataSource dynamicDataSource = new DynamicRoutingDataSource();
        dynamicDataSource.setDefaultTargetDataSource(master());//default数据源
        Map<Object, Object> map = new HashMap<>(8);
        map.put("master", master());
        map.put("slave", slave());
        dynamicDataSource.setTargetDataSources(map);
        return dynamicDataSource;
    }

}
