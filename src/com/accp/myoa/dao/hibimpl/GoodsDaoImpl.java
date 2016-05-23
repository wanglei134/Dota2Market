/**
 * University Of Chongqing.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.accp.myoa.dao.hibimpl;

import java.util.List;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.accp.myoa.dao.GoodsDao;
import com.accp.myoa.entity.Goods;

/**
 * GoodsDaoImpl
 * @author wanglei
 * @version $Id: GoodsDaoImpl.java, v 0.1 2016年4月17日 下午1:44:16 wanglei Exp $
 */
public class GoodsDaoImpl extends BaseHibernateDao implements GoodsDao {

    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    
    public Object getResult(final String hql) {
        return super.getResult(hql);
    }
    public int getCount(final String hql) {
        return super.getCount(hql);
    }

    public void setThreadPoolTaskExecutor(ThreadPoolTaskExecutor threadPoolTaskExecutor) {
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
    }
    
   public List<Goods> findByPage(String hql,int pageSize,int pageNum){
       int firstResult=pageSize*(pageNum-1);
       return super.findByPage(hql, firstResult, pageSize);
   }

    /** 
     * @see com.accp.myoa.dao.GoodsDao#add(com.accp.myoa.entity.Goods)
     */
    public void add(Goods goods) {
        super.getHibernateTemplate().save(goods);
    }

    /** 
     * @see com.accp.myoa.dao.GoodsDao#del(int)
     */
    public void del(int id) {
        super.getHibernateTemplate().delete(load(id));
    }

    /** 
     * @see com.accp.myoa.dao.GoodsDao#update(com.accp.myoa.entity.Goods)
     */
    public void update(Goods goods) {
        getHibernateTemplate().update(goods);
    }

    /** 
     * @see com.accp.myoa.dao.GoodsDao#load(int)
     */
    public Goods load(int id) {
        return (Goods) getHibernateTemplate().load(Goods.class, id);
    }

    /** 
     * @see com.accp.myoa.dao.GoodsDao#loadList(com.accp.myoa.entity.Goods)
     */
    public List<Goods> loadList(Goods goods) {
        if (goods != null) {
            return getHibernateTemplate().findByExample(goods);
        }
        return getHibernateTemplate().loadAll(Goods.class);
    }

    /** 
     * @see com.accp.myoa.dao.GoodsDao#get(int)
     */
    public Goods get(int id) {
        return (Goods) getHibernateTemplate().get(Goods.class, id);
    }
    /** 
     * @see com.accp.myoa.dao.GoodsDao#fetchData(java.lang.String, java.lang.String)
     */
    public String fetchData(String steamId, String type) {
        return null;
    }

  /** 
     * @see com.accp.myoa.dao.GoodsDao#fetchData(java.lang.String, java.lang.String)
     */
   /* public String fetchData(String steamId, String type) {
            if(steamId.trim().length()==0){
                return "输入不合法";
            }
            try {
               List<tmpGoods>  goodsList = HttpUtil.fetchGoods(steamId, type,threadPoolTaskExecutor);
                if (goodsList.size() > 0) {                   
                    super.getHibernateTemplate().saveOrUpdateAll(goodsList);
                    hql = "from Goods g where g.steamId=? and g.type=? and tradable=?";
                    params = new Object[] { steamId, type,1};
                    List<Goods> list2 = super.getHibernateTemplate().find(hql, params);
                    return "数据采集完毕";
                }
                return "没有数据";
                //否则写数据库且返回
            } catch (Exception e) {
                return "出现异常,请刷新页面重试!";
            }
        } 
     */
}
