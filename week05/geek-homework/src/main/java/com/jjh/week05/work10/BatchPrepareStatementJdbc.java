package com.jjh.week05.work10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: geek-homework
 * @description
 * @author: jjh
 * @create: 2021-12-05 16:23
 **/
public class BatchPrepareStatementJdbc {


    public static void main(String[] args) {

        List<Map> values = new ArrayList<>();
        Map user1 = new HashMap<String,String>(8);
        Map user2 = new HashMap<String,String>(8);
        user1.put("id","666");
        user1.put("name","user6");
        user2.put("id","777");
        user2.put("name","user7");
        values.add(user1);
        values.add(user2);

        Connection connection = null;
        PreparedStatement pstmt = null;
        try {
            //1.注册驱动
//            Class.forName("com.mysql.jdbc.Driver");
            //2.拿到连接
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/geekhomework",
                    "root", "123456");
            //3.开启事务
            connection.setAutoCommit(false);
            //4.创建一个Statement对象
            String sql = "INSERT INTO frame_user (id,name) VALUES(?,?)";
            pstmt = connection.prepareStatement(sql);
            //批处理插入数据
            for (Map user : values){
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
