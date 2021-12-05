package com.jjh.week05.work10;

import java.sql.*;

/**
 * @program: geek-homework
 * @description
 * @author: jjh
 * @create: 2021-12-05 16:23
 **/
public class UsePrimordialJdbc {
    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;

        try {
            //1.注册驱动
//            Class.forName("com.mysql.jdbc.Driver");
            //2.拿到连接
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/geekhomework",
                    "root", "123456");
            //3.创建一个Statement对象
            statement = connection.createStatement();
            String sql = "select id,name from frame_user";
            //4.执行sql 取得结果
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                //打印数据
                System.out.println("id: " + resultSet.getString("id"));
                System.out.println("name: " + resultSet.getString("name"));
            }
            //5.关闭连接
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (statement != null){
                try {
                    statement.close();
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
