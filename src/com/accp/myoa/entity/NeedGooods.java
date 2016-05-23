/**
 * University Of Chongqing.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.accp.myoa.entity;

/**
 * 
 * @author wanglei
 * @version $Id: NeedGooods.java, v 0.1 2016年5月11日 下午2:37:16 wanglei Exp $
 */
public class NeedGooods {
    private int id;
    private String name;
    private String hero;
    private String quality;
    private String rarity;
    private String img;
    private float price;
    private String type;
    private String description;
    private int needCount;
    private String desImg;
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getHero() {
        return hero;
    }
    public String getQuality() {
        return quality;
    }
    public String getRarity() {
        return rarity;
    }
    public String getImg() {
        return img;
    }
    public float getPrice() {
        return price;
    }
    public String getType() {
        return type;
    }
    public String getDescription() {
        return description;
    }
    public int getNeedCount() {
        return needCount;
    }
    public String getDesImg() {
        return desImg;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHero(String hero) {
        this.hero = hero;
    }
    public void setQuality(String quality) {
        this.quality = quality;
    }
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public void setPrice(float price) {
        this.price = price;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setNeedCount(int needCount) {
        this.needCount = needCount;
    }
    public void setDesImg(String desImg) {
        this.desImg = desImg;
    }
    
    

}
