package com.jjh.shardingsphere.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: geek-homework-week07
 * @description
 * @author: jjh
 * @create: 2021-12-20 21:40
 **/
@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private DataSource dataSource;

    @Override
    public Map findUser(String userid) {
        ResultSet resultSet = null;
        Connection connection;
        PreparedStatement pstmt;
        try {
            String sql = "SELECT * from t_user where login_id = ? ";
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "jjh");
            resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                //打印数据
                System.out.println("ShardingSphere-JDBC| 读取slave库，取得的用户名是:" + resultSet.getString("user_name"));
            }
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return new HashMap();
    }

    @Override
    public boolean insertUser(String loginid) {
        Connection connection;
        PreparedStatement pstmt;
        try {
            String sql = "INSERT INTO `t_user` VALUES ('5', '2021-12-14 22:26:40', '2021-12-14 22:26:44', '1', ?, 'uu', 'user_master', 'u', '1', '1', '1')";
            connection = dataSource.getConnection();
            pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, "jjh");
            System.out.println("ShardingSphere-JDBC| 插入master库，向表t_user插入了一条用户名为：user_master的记录");
            return pstmt.execute();
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return false;
    }


}
