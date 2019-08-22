package com.ljl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 渠道表
 * </p>
 *
 * @author lvjunlong
 * @since 2019-08-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("uchannel")
public class Channel implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 用户唯一标识
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 渠道ID
     */
    @TableField("channelID")
    private Integer channelID;

    /**
     * 游戏ID
     */
    @TableField("appID")
    private Integer appID;

    /**
     * 渠道分配给游戏的appID
     */
    @TableField("cpAppID")
    private String cpAppID;

    /**
     * 渠道分配给游戏的AppKey
     */
    @TableField("cpAppKey")
    private String cpAppKey;

    /**
     * 渠道分配给游戏的AppSecret
     */
    @TableField("cpAppSecret")
    private String cpAppSecret;

    /**
     * 渠道分配给游戏的cpID
     */
    @TableField("cpID")
    private String cpID;

    /**
     * 渠道分配给游戏的支付ID
     */
    @TableField("cpPayID")
    private String cpPayID;

    /**
     * 渠道分配给游戏的支付公钥
     */
    @TableField("cpPayKey")
    private String cpPayKey;

    /**
     * 渠道分配给游戏的支付私钥
     */
    @TableField("cpPayPriKey")
    private String cpPayPriKey;

    /**
     * 渠道商ID
     */
    @TableField("masterID")
    private Integer masterID;

    /**
     * 部分渠道可能有特殊配置信息
     */
    @TableField("cpConfig")
    private String cpConfig;

    /**
     * 当前SDK登录认证地址
     */
    @TableField("authUrl")
    private String authUrl;

    /**
     * 当前SDK支付通知回调地址
     */
    @TableField("payCallbackUrl")
    private String payCallbackUrl;

    /**
     * SDK订单号获取地址，没有则为空
     */
    @TableField("orderUrl")
    private String orderUrl;

    /**
     * 当前SDK的验证处理类的全类名
     */
    @TableField("verifyClass")
    private String verifyClass;

    /**
     * 充值功能状态0：开放；1：关闭
     */
    @TableField("openPayFlag")
    private Integer openPayFlag;

    @TableField("registeredSwitch")
    private Boolean registeredSwitch;

    /**
     * 渠道参数配置(json格式)
     */
    @TableField("channelParams")
    private String channelParams;

    @TableField("osType")
    private Integer osType;

    @TableField("activateSwitch")
    private Integer activateSwitch;

    @TableField("loginSwitch")
    private Integer loginSwitch;


}
