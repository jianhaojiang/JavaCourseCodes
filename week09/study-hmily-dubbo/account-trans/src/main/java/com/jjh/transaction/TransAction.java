package com.jjh.transaction;

import java.math.BigDecimal;

/**
 * @program: study-hmily-dubbo
 * @description
 * @author: jjh
 * @create: 2022-02-20 13:40
 **/
public interface TransAction {

    /**
     * 人民币兑换为美元 TCC-try
     * @param userId
     * @param money
     * @param transNo
     */
    void tryTransRmbToUsdAction(Long userId, BigDecimal money, String transNo);


    /**
     * 人民币兑换为美元 TCC-confirm
     * @param userId
     * @param money
     * @param transNo
     */
    void confirmTransRmbToUsdAction(Long userId, BigDecimal money, String transNo);


    /**
     * 人民币兑换为美元 TCC-cancel
     * @param userId
     * @param money
     * @param transNo
     */
    void cancelTransRmbToUsdAction(Long userId, BigDecimal money, String transNo);


    /**
     * 美元兑换为人民币 TCC-try
     * @param userId
     * @param money
     * @param transNo
     */
    void tryTransUsdToRmbAction(Long userId, BigDecimal money, String transNo);


    /**
     * 美元兑换为人民币 TCC-confirm
     * @param userId
     * @param money
     * @param transNo
     */
    void confirmTransUsdToRmbAction(Long userId, BigDecimal money, String transNo);

    /**
     * 美元兑换为人民币 TCC-cancel
     * @param userId
     * @param money
     * @param transNo
     */
    void cancelTransUsdToRmbAction(Long userId, BigDecimal money, String transNo);


}
