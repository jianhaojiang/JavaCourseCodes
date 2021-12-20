package com.jjh.springroute.service;

import java.sql.ResultSet;
import java.util.Map;

/**
 * @program: geek-homework-week07
 * @description
 * @author: jjh
 * @create: 2021-12-20 21:35
 **/
public interface IUserService {

    /**
     * 查询数据
     * @param userid
     * @return
     */
    public Map findUser(String userid);

    /**
     * 插入数据
     * @param loginid
     * @return
     */
    public boolean insertUser(String loginid);

}
