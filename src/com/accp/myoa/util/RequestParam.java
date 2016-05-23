package com.accp.myoa.util;
public class RequestParam{
    //更新时间
    private Long createTime;
    
    /**
     * Setter method for property <tt>createTime</tt>.
     * 
     * @param createTime value to be assigned to property createTime
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }
    /**
     * Getter method for property <tt>createTime</tt>.
     * 
     * @return property value of createTime
     */
    public Long getCreateTime() {
        return createTime;
    }
    private String steamId;
    public String getSteamId() {
        return steamId;
    }
    public void setSteamId(String steamId) {
        this.steamId = steamId;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getIsGuoFuId() {
        return isGuoFuId;
    }
    public void setIsGuoFuId(String isGuoFuId) {
        this.isGuoFuId = isGuoFuId;
    }
    private String type;
    private String isGuoFuId;
    
}
