/**
 * University Of Chongqing.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.accp.myoa.entity;

import java.util.Date;
import java.util.List;

/**
 * TradeLog
 * @author wanglei
 * @version $Id: TradeLog.java, v 0.1 2016年5月11日 下午2:47:45 wanglei Exp $
 */
public class TradeLog {
    private String id;
    private Users tradeUser;
    private List<NeedGooods> tradeGoods;
    private Date tradeTime;
    private String tradeMark;
    
    public String getId() {
        return id;
    }
    public Users getTradeUser() {
        return tradeUser;
    }
    public List<NeedGooods> getTradeGoods() {
        return tradeGoods;
    }
    public Date getTradeTime() {
        return tradeTime;
    }
    public String getTradeMark() {
        return tradeMark;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setTradeUser(Users tradeUser) {
        this.tradeUser = tradeUser;
    }
    public void setTradeGoods(List<NeedGooods> tradeGoods) {
        this.tradeGoods = tradeGoods;
    }
    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }
    public void setTradeMark(String tradeMark) {
        this.tradeMark = tradeMark;
    }
}
