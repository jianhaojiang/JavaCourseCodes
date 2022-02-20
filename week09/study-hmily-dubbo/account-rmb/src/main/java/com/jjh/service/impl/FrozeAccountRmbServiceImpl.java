package com.jjh.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jjh.common.entity.AccountRmb;
import com.jjh.common.entity.FrozeAccountRmb;
import com.jjh.common.mapper.FrozeAccountRmbMapper;
import com.jjh.common.service.AccountRmbService;
import com.jjh.common.service.FrozeAccountRmbService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @program: study-hmily-dubbo
 * @description
 * @author: jjh
 * @create: 2022-02-20 10:25
 **/
@DubboService(version = "1.0.0", interfaceClass = FrozeAccountRmbService.class, timeout = 5000)
public class FrozeAccountRmbServiceImpl
        extends ServiceImpl<FrozeAccountRmbMapper, FrozeAccountRmb>
        implements FrozeAccountRmbService {

    @Autowired
    AccountRmbService accountRmbService;

    @Override
    public boolean addFroze(Long userId, BigDecimal money, String transNo) {
        //1.账号查询
        AccountRmb accountRmb = accountRmbService.getOne(new LambdaQueryWrapper<AccountRmb>().eq(AccountRmb::getUserId, userId));
        if (accountRmb == null) {
            throw new RuntimeException("账户不存在！");
        }

        //2.插入冻结表记录
        FrozeAccountRmb frozeAccountRmb = new FrozeAccountRmb();
        frozeAccountRmb.setFrozeMoney(money);
        frozeAccountRmb.setTransNo(transNo);
        frozeAccountRmb.setUserId(userId);

        //3.更新数据
        return save(frozeAccountRmb);
    }

    @Override
    public boolean removeFroze(String transNo) {
        return remove(new LambdaQueryWrapper<FrozeAccountRmb>().eq(FrozeAccountRmb::getTransNo, transNo));
    }

}
