package com.accp.myoa.entity;

// default package
// Generated 2016-5-7 16:49:40 by Hibernate Tools 4.3.1.Final

/**
 * Userinfo generated by hbm2java
 */
public class Userinfo implements java.io.Serializable {

    private Integer id;
    private String  ip;
    private String  city;
    private String  browser;
    private String  steamid;

    public Userinfo() {
    }

    public Userinfo(String ip, String city, String browser, String steamid) {
        this.ip = ip;
        this.city = city;
        this.browser = browser;
        this.steamid = steamid;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBrowser() {
        return this.browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getSteamid() {
        return this.steamid;
    }

    public void setSteamid(String steamid) {
        this.steamid = steamid;
    }

}