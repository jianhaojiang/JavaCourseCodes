package com.jjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jjh.common.entity.*;
import com.jjh.common.entity.AccountUsd;
import com.jjh.common.mapper.AccountUsdMapper;
import com.jjh.common.service.AccountUsdService;
import com.jjh.common.service.FrozeAccountUsdService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @program: study-hmily-dubbo
 * @description
 * @author: jjh
 * @create: 2022-02-20 11:18
 **/
@DubboService(version = "1.0.0", interfaceClass = AccountUsdService.class, timeout = 5000)
public class AccountUsdServiceImpl extends ServiceImpl<AccountUsdMapper, AccountUsd> implements AccountUsdService {

    @Override
    public void add(Long userId, BigDecimal money) {
        //1.账号查询
        AccountUsd accountUsd = getOne(new LambdaQueryWrapper<AccountUsd>().eq(AccountUsd::getUserId, userId));
        if (accountUsd == null) {
            throw new RuntimeException("账户不存在！");
        }
        //2.将余额加入
        accountUsd.setMoney(accountUsd.getMoney().add(money));
        //3.更新余额
        updateById(accountUsd);
    }

    @Override
    public void sub(Long userId, BigDecimal money) {
        //1.账号查询
        AccountUsd accountUsd = getOne(new LambdaQueryWrapper<AccountUsd>().eq(AccountUsd::getUserId, userId));
        if (accountUsd == null) {
            throw new RuntimeException("账户不存在！");
        }
        if (accountUsd.getMoney().compareTo(money) < 0) {
            throw new RuntimeException("账户余额不足！");
        }
        //2.扣除余额
        accountUsd.setMoney(accountUsd.getMoney().subtract(money));
        //3.更新余额
        updateById(accountUsd);
    }


}
