package com.jjh.controller;

import com.jjh.transaction.TransAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * @program: study-hmily-dubbo
 * @description
 * @author: jjh
 * @create: 2022-02-20 13:38
 **/
@RestController
@RequestMapping
public class TransController {

    @Autowired
    TransAction transAction;

    @GetMapping("/trans")
    public Object trans(){
        try {
            //userId=1账户1美元兑换为人民币
//            transAction.tryTransUsdToRmbAction(1L, new BigDecimal(1), UUID.randomUUID().toString());
            //userId=2账户7人民币兑换为美元
            transAction.tryTransRmbToUsdAction(2L, new BigDecimal(7), UUID.randomUUID().toString());
        }catch (Exception e){
            e.printStackTrace();
            return "转账失败！";
        }
        return "转账已完成！";
    }


}
