package com.jjh.transaction;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jjh.common.entity.FrozeAccountRmb;
import com.jjh.common.entity.FrozeAccountUsd;
import com.jjh.common.service.AccountRmbService;
import com.jjh.common.service.AccountUsdService;
import com.jjh.common.service.FrozeAccountRmbService;
import com.jjh.common.service.FrozeAccountUsdService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * @program: study-hmily-dubbo
 * @description
 * @author: jjh
 * @create: 2022-02-20 14:02
 **/
@Component
public class TransActionImpl implements TransAction {

    private BigDecimal rate = new BigDecimal(7);

    @DubboReference(version = "1.0.0", interfaceClass = AccountUsdService.class)
    private AccountUsdService accountUsdService;
    @DubboReference(version = "1.0.0", interfaceClass = AccountRmbService.class)
    private AccountRmbService accountRmbService;

    @DubboReference(version = "1.0.0", interfaceClass = FrozeAccountRmbService.class)
    private FrozeAccountRmbService frozeAccountRmbService;
    @DubboReference(version = "1.0.0", interfaceClass = FrozeAccountUsdService.class)
    private FrozeAccountUsdService frozeAccountUsdService;

    @HmilyTCC(confirmMethod = "confirmTransRmbToUsdAction", cancelMethod = "cancelTransRmbToUsdAction")
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void tryTransRmbToUsdAction(Long userId, BigDecimal money, String transNo) {
        System.out.println("人民币兑换为美元——Try|"+transNo);
        //扣除人民币账户余额，划到人民币账户冻结表
        accountRmbService.sub(userId, money);
        frozeAccountRmbService.addFroze(userId, money, transNo);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void confirmTransRmbToUsdAction(Long userId, BigDecimal money, String transNo) {
        System.out.println("人民币兑换为美元——Confirm|"+transNo);
        //将人民币账户的余额转换后加入到美元账户
        accountUsdService.add(userId, money.divide(rate));
        //删除人民币冻结记录
        frozeAccountRmbService.removeFroze(transNo);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void cancelTransRmbToUsdAction(Long userId, BigDecimal money, String transNo) {
        System.out.println("人民币兑换为美元——Cancel|"+transNo);
        FrozeAccountRmb frozeAccountRmb = frozeAccountRmbService.getOne(new LambdaQueryWrapper<FrozeAccountRmb>().eq(FrozeAccountRmb::getTransNo, transNo));
        if (frozeAccountRmb != null){
            //将金额加回到人民币账户
            accountRmbService.add(userId, money);
            //删除人民币冻结记录
            frozeAccountRmbService.removeFroze(transNo);
        }
    }

    @HmilyTCC(confirmMethod = "confirmTransUsdToRmbAction", cancelMethod = "cancelTransUsdToRmbAction")
    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void tryTransUsdToRmbAction(Long userId, BigDecimal money, String transNo) {
        System.out.println("美元兑换为人民币——Try|"+transNo);
        //扣除美元账户余额，划到美元账户冻结表
        accountUsdService.sub(userId, money);
        frozeAccountUsdService.addFroze(userId, money, transNo);

    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void confirmTransUsdToRmbAction(Long userId, BigDecimal money, String transNo) {
        System.out.println("美元兑换为人民币——Confirm|"+transNo);
        //将美元账户的余额转换后加入到人民币账户
        accountRmbService.add(userId, money.multiply(rate));
        //删除美元冻结记录
        frozeAccountUsdService.removeFroze(transNo);
    }

    @Transactional(rollbackFor = Throwable.class)
    @Override
    public void cancelTransUsdToRmbAction(Long userId, BigDecimal money, String transNo) {
        System.out.println("美元兑换为人民币——rollback|"+transNo);
        FrozeAccountUsd frozeAccountUsd = frozeAccountUsdService.getOne(new LambdaQueryWrapper<FrozeAccountUsd>().eq(FrozeAccountUsd::getTransNo, transNo));
        if (frozeAccountUsd != null){
            //将金额加回到美元账户
            accountUsdService.add(userId, money);
            //删除美元冻结记录
            frozeAccountUsdService.removeFroze(transNo);
        }
    }
}
