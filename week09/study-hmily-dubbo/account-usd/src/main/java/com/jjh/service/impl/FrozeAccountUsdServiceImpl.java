package com.jjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jjh.common.entity.AccountUsd;
import com.jjh.common.entity.FrozeAccountUsd;
import com.jjh.common.mapper.FrozeAccountUsdMapper;
import com.jjh.common.service.AccountUsdService;
import com.jjh.common.service.FrozeAccountUsdService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @program: study-hmily-dubbo
 * @description
 * @author: jjh
 * @create: 2022-02-20 11:13
 **/
@DubboService(version = "1.0.0", interfaceClass = FrozeAccountUsdService.class, timeout = 5000)
public class FrozeAccountUsdServiceImpl
        extends ServiceImpl<FrozeAccountUsdMapper, FrozeAccountUsd>
        implements FrozeAccountUsdService {


    @Autowired
    AccountUsdService accountUsdService;

    @Override
    public boolean addFroze(Long userId, BigDecimal money, String transNo) {
        //1.账号查询
        AccountUsd accountUsd = accountUsdService.getOne(new LambdaQueryWrapper<AccountUsd>().eq(AccountUsd::getUserId, userId));
        if (accountUsd == null) {
            throw new RuntimeException("账户不存在！");
        }

        //2.插入冻结表记录
        FrozeAccountUsd frozeAccountUsd = new FrozeAccountUsd();
        frozeAccountUsd.setFrozeMoney(money);
        frozeAccountUsd.setTransNo(transNo);
        frozeAccountUsd.setUserId(userId);

        //3.更新数据
        return save(frozeAccountUsd);
    }

    @Override
    public boolean removeFroze(String transNo) {
        return remove(new LambdaQueryWrapper<FrozeAccountUsd>().eq(FrozeAccountUsd::getTransNo, transNo));
    }

}
