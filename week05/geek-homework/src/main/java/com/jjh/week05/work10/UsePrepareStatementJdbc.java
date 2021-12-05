package com.jjh.week05.work10;

import java.sql.*;

/**
 * @program: geek-homework
 * @description
 * @author: jjh
 * @create: 2021-12-05 16:23
 **/
public class UsePrepareStatementJdbc {
    public static void main(String[] args) {

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
            pstmt.setString(1, "333");
            pstmt.setString(2, "user3");
            //5.执行sql 取得结果
            int insRow = pstmt.executeUpdate();
            System.out.println("插入了"+insRow+"行数据");
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
