package com.jjh.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jjh.common.entity.FrozeAccountUsd;

import java.math.BigDecimal;

/**
 * @program: study-hmily-dubbo
 * @description 冻结账户服务类
 * @author: jjh
 * @create: 2022-02-19 17:43
 **/
public interface FrozeAccountUsdService extends IService<FrozeAccountUsd> {

    /**
     * 增加冻结表记录
     * @param userId
     * @param money
     * @param transNo
     */
    boolean addFroze(Long userId, BigDecimal money, String transNo);

    /**
     * 删除冻结表记录
     * @param transNo
     */
    boolean removeFroze(String transNo);

}
