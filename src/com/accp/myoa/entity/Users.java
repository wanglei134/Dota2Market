package com.accp.myoa.entity;

import java.util.Set;

// default package
// Generated 2016-5-2 2:18:28 by Hibernate Tools 4.3.1.Final

/**
 * Users generated by hbm2java
 */
public class Users {

    private Integer       id;
    private String        steamid;
    private String        communityvisibilitystate;
    private String        profilestate;
    private String        personaname;
    private String        lastlogoff;
    private String        profileurl;
    private String        avatar;
    private String        avatarmedium;
    private String        avatarfull;
    private String        personastate;
    private String        primaryclanid;
    private String        timecreated;
    private String        personastateflags;
    private String        tradeUrl;
    private Set<TradeLog> tradeLogs;

    /**
     * 支付宝账号和账号姓名
     */
    private String        aliPayAccountName;
    private String        alipayAccountNo;
    /**
     * 保存金额的100倍,比如12.12元,存到数据库就是1212,这样精度会准确点
     */
    private Integer       money = new Integer(0);

    /**
     * Setter method for property <tt>aliPayAccountName</tt>.
     * 
     * @param aliPayAccountName value to be assigned to property aliPayAccountName
     */
    public void setAliPayAccountName(String aliPayAccountName) {
        this.aliPayAccountName = aliPayAccountName;
    }

    /**
     * Getter method for property <tt>aliPayAccountName</tt>.
     * 
     * @return property value of aliPayAccountName
     */
    public String getAliPayAccountName() {
        return aliPayAccountName;
    }

    /**
     * Setter method for property <tt>alipayAccountNo</tt>.
     * 
     * @param alipayAccountNo value to be assigned to property alipayAccountNo
     */
    public void setAlipayAccountNo(String alipayAccountNo) {
        this.alipayAccountNo = alipayAccountNo;
    }

    /**
     * Getter method for property <tt>alipayAccountNo</tt>.
     * 
     * @return property value of alipayAccountNo
     */
    public String getAlipayAccountNo() {
        return alipayAccountNo;
    }

    /**
    * Setter method for property <tt>tradeLogs</tt>.
    * 
    * @param tradeLogs value to be assigned to property tradeLogs
    */
    public void setTradeLogs(Set<TradeLog> tradeLogs) {
        this.tradeLogs = tradeLogs;
    }

    /**
     * Getter method for property <tt>tradeLogs</tt>.
     * 
     * @return property value of tradeLogs
     */
    public Set<TradeLog> getTradeLogs() {
        return tradeLogs;
    }

    /**
     * Setter method for property <tt>tradeUrl</tt>.
     * 
     * @param tradeUrl value to be assigned to property tradeUrl
     */
    public void setTradeUrl(String tradeUrl) {
        this.tradeUrl = tradeUrl;
    }

    /**
     * Getter method for property <tt>tradeUrl</tt>.
     * 
     * @return property value of tradeUrl
     */
    public String getTradeUrl() {
        return tradeUrl;
    }

    /**
     * Setter method for property <tt>money</tt>.
     * 
     * @param money value to be assigned to property money
     */
    public void setMoney(Integer money) {
        this.money = money;
    }

    /**
     * Getter method for property <tt>money</tt>.
     * 
     * @return property value of money
     */
    public Integer getMoney() {
        return money;
    }

    public Users() {
    }

    public Users(String steamid, String communityvisibilitystate, String profilestate,
                 String personaname, String lastlogoff, String profileurl, String avatar,
                 String avatarmedium, String avatarfull, String personastate, String primaryclanid,
                 String timecreated, String personastateflags, Integer money,
                 Set<TradeLog> tradeLogs) {
        this.steamid = steamid;
        this.communityvisibilitystate = communityvisibilitystate;
        this.profilestate = profilestate;
        this.personaname = personaname;
        this.lastlogoff = lastlogoff;
        this.profileurl = profileurl;
        this.avatar = avatar;
        this.avatarmedium = avatarmedium;
        this.avatarfull = avatarfull;
        this.personastate = personastate;
        this.primaryclanid = primaryclanid;
        this.timecreated = timecreated;
        this.personastateflags = personastateflags;
        this.money = money;
        this.tradeLogs = tradeLogs;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSteamid() {
        return this.steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

    public String getCommunityvisibilitystate() {
        return this.communityvisibilitystate;
    }

    public void setCommunityvisibilitystate(String communityvisibilitystate) {
        this.communityvisibilitystate = communityvisibilitystate;
    }

    public String getProfilestate() {
        return this.profilestate;
    }

    public void setProfilestate(String profilestate) {
        this.profilestate = profilestate;
    }

    public String getPersonaname() {
        return this.personaname;
    }

    public void setPersonaname(String personaname) {
        this.personaname = personaname;
    }

    public String getLastlogoff() {
        return this.lastlogoff;
    }

    public void setLastlogoff(String lastlogoff) {
        this.lastlogoff = lastlogoff;
    }

    public String getProfileurl() {
        return this.profileurl;
    }

    public void setProfileurl(String profileurl) {
        this.profileurl = profileurl;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatarmedium() {
        return this.avatarmedium;
    }

    public void setAvatarmedium(String avatarmedium) {
        this.avatarmedium = avatarmedium;
    }

    public String getAvatarfull() {
        return this.avatarfull;
    }

    public void setAvatarfull(String avatarfull) {
        this.avatarfull = avatarfull;
    }

    public String getPersonastate() {
        return this.personastate;
    }

    public void setPersonastate(String personastate) {
        this.personastate = personastate;
    }

    public String getPrimaryclanid() {
        return this.primaryclanid;
    }

    public void setPrimaryclanid(String primaryclanid) {
        this.primaryclanid = primaryclanid;
    }

    public String getTimecreated() {
        return this.timecreated;
    }

    public void setTimecreated(String timecreated) {
        this.timecreated = timecreated;
    }

    public String getPersonastateflags() {
        return this.personastateflags;
    }

    public void setPersonastateflags(String personastateflags) {
        this.personastateflags = personastateflags;
    }

}
