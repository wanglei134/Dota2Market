package com.accp.myoa.entity;

import java.math.BigDecimal;
import java.util.List;

// default package
// Generated 2016-5-2 2:18:28 by Hibernate Tools 4.3.1.Final

/**
 * Users generated by hbm2java
 */
public class Users implements java.io.Serializable {

    private Integer id;
    private String  steamid;
    private String  communityvisibilitystate;
    private String  profilestate;
    private String  personaname;
    private String  lastlogoff;
    private String  profileurl;
    private String  avatar;
    private String  avatarmedium;
    private String  avatarfull;
    private String  personastate;
    private String  primaryclanid;
    private String  timecreated;
    private String  personastateflags;
    private BigDecimal money=new BigDecimal(0.0);
    private List<Tmp> kucun;
    /**
     * Setter method for property <tt>kucun</tt>.
     * 
     * @param kucun value to be assigned to property kucun
     */
    public void setKucun(List<Tmp> kucun) {
        this.kucun = kucun;
    }
    /**
     * Getter method for property <tt>kucun</tt>.
     * 
     * @return property value of kucun
     */
    public List<Tmp> getKucun() {
        return kucun;
    }
    
    /**
     * Setter method for property <tt>money</tt>.
     * 
     * @param money value to be assigned to property money
     */
    public void setMoney(BigDecimal money) {
        this.money = money;
    }
    /**
     * Getter method for property <tt>money</tt>.
     * 
     * @return property value of money
     */
    public BigDecimal getMoney() {
        return money;
    }

    public Users() {
    }

    public Users(String steamid, String communityvisibilitystate, String profilestate,
                 String personaname, String lastlogoff, String profileurl, String avatar,
                 String avatarmedium, String avatarfull, String personastate, String primaryclanid,
                 String timecreated, String personastateflags) {
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
