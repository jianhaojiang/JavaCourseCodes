package com.jjh.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jjh.common.entity.AccountUsd;

import java.math.BigDecimal;

/**
 * @program: study-hmily-dubbo
 * @description usd账户服务类
 * @author: jjh
 * @create: 2022-02-19 17:43
 **/
public interface AccountUsdService extends IService<AccountUsd> {

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
