package com.jjh.week05.work10;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UseHikariApplication {


    public static void main(String[] args) {
        //Hikari 配置
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
        hikariConfig.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/geekhomework");
        hikariConfig.setUsername("root");
        hikariConfig.setPassword("123456");
        hikariConfig.setMinimumIdle(1);
        hikariConfig.setMaximumPoolSize(4);
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        List<Map> values = new ArrayList<>();
        Map user1 = new HashMap<String, String>(8);
        Map user2 = new HashMap<String, String>(8);
        user1.put("id", "666");
        user1.put("name", "user6");
        user2.put("id", "777");
        user2.put("name", "user7");
        values.add(user1);
        values.add(user2);

        Connection connection = null;
        PreparedStatement pstmt = null;

        try {
            connection = dataSource.getConnection();
            //3.开启事务
            connection.setAutoCommit(false);
            //4.创建一个Statement对象
            String sql = "INSERT INTO frame_user (id,name) VALUES(?,?)";
            pstmt = connection.prepareStatement(sql);
            //批处理插入数据
            for (Map user : values) {
                pstmt.setString(1, String.valueOf(user.get("id")));
                pstmt.setString(2, String.valueOf(user.get("name")));
                pstmt.addBatch();
            }
            //5.执行sql
            pstmt.executeBatch();
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
