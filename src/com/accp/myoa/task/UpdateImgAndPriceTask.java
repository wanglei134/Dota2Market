/**
 * University Of Chongqing.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.accp.myoa.task;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.TimerTask;

import com.accp.myoa.dao.GoodsDao;
import com.accp.myoa.dao.TmpDao;
import com.accp.myoa.entity.Goods;
import com.accp.myoa.util.HttpUtil;
import com.accp.myoa.util.ImageUtil;

/**
 * UpdateImgAndPriceTask
 * @author wanglei
 * @version $Id: UpdateImgAndPriceTask.java, v 0.1 2016年4月23日 下午3:13:37 wanglei Exp $
 */
public class UpdateImgAndPriceTask extends TimerTask {

    private GoodsDao goodsDao;
    private TmpDao tmpDao;
       
    /**
     * Setter method for property <tt>tmpDao</tt>.
     * 
     * @param tmpDao value to be assigned to property tmpDao
     */
    public void setTmpDao(TmpDao tmpDao) {
        this.tmpDao = tmpDao;
    }

    /**
    * Setter method for property <tt>goodsDao</tt>.
    * 
    * @param goodsDao value to be assigned to property goodsDao
    */
    public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    /** 
     * @see java.util.TimerTask#run()
     */
    public void run() {
       // String hql="from Goods where price='0.0' or iconBase64 is NULL";
        //新逻辑只更新图片了
        String hql="from Goods where  iconBase64 is NULL";
        int c=goodsDao.getCount(hql);
        List<Goods> goodsList=goodsDao.findByPage(hql, c, 1);
        System.out.println("后台更新线程开始工作了" +",需要更新的数量:"+goodsList.size()+":"+ goodsDao);
        for (Goods goods : goodsList) { 
            try {
                Thread.sleep(100);
            } catch (InterruptedException e2) {
               e2.printStackTrace();
            }
            //更新图片
            if(goods.getIconBase64()==null){
                System.out.println("尝试更新图片");
                byte[] imgByte = null;
                try {                   
                    imgByte = ImageUtil.scale2((InputStream)HttpUtil.getJsonContent(goods.getImgurl(), 2), 62, 62, true);
                    goods.setIconBase64(HttpUtil.readInputStream(new ByteArrayInputStream(imgByte)));
                    System.out.println("成功更新图片信息");
                    goodsDao.update(goods);
                } catch (Exception e1) {
                    System.out.println("后台线程,获取图片超时");  
                }
            }
/*            if(goods.getPrice().equals("0.0")){
                System.out.println("尝试更新价格");
                try {
                    //组装查价格链接
                    String priceUrl = "http://steamcommunity.com/market/priceoverview/?country=CN&currency=1&appid="
                                      + goods.getType() + "&market_hash_name=";
                    String hash_name_url = null;
                    try {
                        hash_name_url = URLEncoder.encode(goods.getName(), "utf-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    } //已经转空格为+了
                    hash_name_url = hash_name_url.replace("+", "%20");
                    priceUrl = priceUrl + hash_name_url;
                    String pricedata = HttpUtil.getJsonContent(priceUrl,3).toString();
                    JSONObject pricejson = JSONObject.parseObject(pricedata);
                    //lower_price
                    if (pricejson != null && pricejson.get("success").toString().trim().equals("true")) {

                        if (pricejson.get("lowest_price") != null) {
                            goods.setPrice(pricejson.get("lowest_price").toString().replace("$", "")
                                .replace("USD", ""));
                            System.out.println("成功更新价格信息");
                        } else if (pricejson.get("median_price") != null) {
                            goods.setPrice(pricejson.get("median_price").toString().replace("$", "")
                                .replace("USD", ""));
                            System.out.println("成功更新价格信息");
                        } else {
                            goods.setPrice("999");
                        }
                       
                        //更新临时库的价格信息
                        //触发器来完成这个工作
                        String hql1="from Tmp where name='"+GoodsAction.encode(goods.getName())+"'";
                        int num=tmpDao.getCount(hql1);
                        List<Tmp> tmpNeedUpdate=tmpDao.findByPage(hql1, num, 1);
                        for (Tmp tmp : tmpNeedUpdate) {
                            tmpDao.update(tmp);
                        }
                    } else if(pricejson != null
                            && pricejson.get("success").toString().trim().equals("false")){
                        goods.setPrice("999");
                    }
                    goods.setLastupdatetime(new Date());
                    goodsDao.update(goods);
                } catch (Exception e) {
                    System.out.println("后台线程,异常,未能查询到价格!"+e);
                    HttpUtil.setProxy();
                }  
            }  */       
        }
       
    }

}
