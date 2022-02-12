package com.jjh.work02;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * 使用 Hikari 连接池
 * 连接 shardingsphere-proxy 代理出来的 mysql 数据库
 */
public class UseHikariApplication {


    public static void main(String[] args) {
        //Hikari 配置
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://127.0.0.1:3307/sharding_db");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("123456");
        hikariConfig.setMinimumIdle(1);
        hikariConfig.setMaximumPoolSize(4);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = dataSource.getConnection();
            //3.开启事务
            connection.setAutoCommit(false);
            //4.创建一个Statement对象
            String sql = "INSERT INTO t_order (create_time,update_time,is_delete,user_id) VALUES(?,?,?,?)";
            pstmt = connection.prepareStatement(sql);
            //插入数据
            pstmt.setString(1, "2022-02-08 21:42:58");
            pstmt.setString(2, "2022-02-08 21:42:58");
            pstmt.setString(3, "0");
            pstmt.setInt(4, 66);
            //5.执行sql
            pstmt.execute();
            //6.提交事务
            connection.commit();
            connection.setAutoCommit(true);
            //7.关闭连接
            pstmt.close();
            connection.close();

        } catch (Exception e) {
            //回滚
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            if (pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
