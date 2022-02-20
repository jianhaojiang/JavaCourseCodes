package com.jjh.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @program: study-hmily-dubbo
 * @description
 * @author: jjh
 * @create: 2022-02-19 17:22
 **/
@Data
@TableName("froze_account_usd")
@ApiModel(value = "FrozeAccountUsd对象", description = "usd冻结账户")
public class FrozeAccountUsd implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户 id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "冻结金额")
    @TableField("froze_money")
    private BigDecimal frozeMoney;

    @ApiModelProperty(value = "转账的单号")
    @TableField("trans_no")
    private String transNo;

    @ApiModelProperty(value = "创建时间")
    @TableField("gmt_create")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    @TableField("gmt_modified")
    private LocalDateTime gmtModified;




}
