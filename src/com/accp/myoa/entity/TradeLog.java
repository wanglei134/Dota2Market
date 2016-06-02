/**
 * University Of Chongqing.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.accp.myoa.entity;

import java.util.Date;

/**
 * TradeLog
 * @author wanglei
 * @version $Id: TradeLog.java, v 0.1 2016年5月11日 下午2:47:45 wanglei Exp $
 */
public class TradeLog {
    private Integer id;
    private Users  tradeUser;
    private String tradeGoods;
    private Date   tradeTime;
    private String tradeMark;
    private String tradeNo;
    private int    tradeMoney;
    private String tradeStatus;

    /**
     * 
     */
    public TradeLog() {
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public int getTradeMoney() {
        return tradeMoney;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public void setTradeMoney(int tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public Integer getId() {
        return id;
    }

    public Users getTradeUser() {
        return tradeUser;
    }

    public String getTradeGoods() {
        return tradeGoods;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public String getTradeMark() {
        return tradeMark;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTradeUser(Users tradeUser) {
        this.tradeUser = tradeUser;
    }

    public void setTradeGoods(String tradeGoods) {
        this.tradeGoods = tradeGoods;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    public void setTradeMark(String tradeMark) {
        this.tradeMark = tradeMark;
    }
}
