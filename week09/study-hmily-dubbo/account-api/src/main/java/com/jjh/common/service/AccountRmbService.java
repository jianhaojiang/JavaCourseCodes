package com.jjh.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jjh.common.entity.AccountRmb;

import java.math.BigDecimal;

/**
 * @program: study-hmily-dubbo
 * @description rmb账户服务类
 * Service接口
 * @author: jjh
 * @create: 2022-02-19 17:37
 **/
public interface AccountRmbService extends IService<AccountRmb> {


    /**
     * 增加余额
     * @param userId
     * @param money
     */
    void add(Long userId, BigDecimal money);

    /**
     * 扣除余额
     * @param userId
     * @param money
     */
    void sub(Long userId, BigDecimal money);

}
