package com.jjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jjh.common.entity.AccountRmb;
import com.jjh.common.entity.FrozeAccountRmb;
import com.jjh.common.mapper.AccountRmbMapper;
import com.jjh.common.service.AccountRmbService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @program: study-hmily-dubbo
 * @description 定义一个dubbo服务
 * @author: jjh
 * @create: 2022-02-20 10:16
 **/
@DubboService(version = "1.0.0", interfaceClass = AccountRmbService.class, timeout = 5000)
public class AccountRmbServiceImpl extends ServiceImpl<AccountRmbMapper, AccountRmb> implements AccountRmbService {

    @Override
    public void add(Long userId, BigDecimal money) {
        //1.账号查询
        AccountRmb accountRmb = getOne(new LambdaQueryWrapper<AccountRmb>().eq(AccountRmb::getUserId, userId));
        if (accountRmb == null) {
            throw new RuntimeException("账户不存在！");
        }
        //2.将余额加入
        accountRmb.setMoney(accountRmb.getMoney().add(money));
        //3.更新余额
        updateById(accountRmb);
    }

    @Override
    public void sub(Long userId, BigDecimal money) {
        //1.账号查询
        AccountRmb accountRmb = getOne(new LambdaQueryWrapper<AccountRmb>().eq(AccountRmb::getUserId, userId));
        if (accountRmb == null) {
            throw new RuntimeException("账户不存在！");
        }
        if (accountRmb.getMoney().compareTo(money) < 0) {
            throw new RuntimeException("账户余额不足！");
        }
        //2.扣除余额
        accountRmb.setMoney(accountRmb.getMoney().subtract(money));
        //3.更新余额
        updateById(accountRmb);
    }


}
