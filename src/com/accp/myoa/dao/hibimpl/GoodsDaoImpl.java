/**
 * University Of Chongqing.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.accp.myoa.dao.hibimpl;

import java.util.List;

import com.accp.myoa.dao.GoodsDao;
import com.accp.myoa.entity.Goods;
import com.accp.myoa.util.HttpUtil;

/**
 * GoodsDaoImpl
 * @author wanglei
 * @version $Id: GoodsDaoImpl.java, v 0.1 2016年4月17日 下午1:44:16 wanglei Exp $
 */
public class GoodsDaoImpl extends BaseHibernateDao implements GoodsDao {

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
    public List<Goods> fetchData(String steamId, String type) {
        //逻辑是
        //1、先判断数据库是否存在该steamID的数据,有的话直接取出来,没有的话需要从网上获取
        String hql = "from Goods g where g.steamId=? and g.type=?";
        String[] params = new String[] { steamId, type };
        List<Goods> goodsList = super.getHibernateTemplate().find(hql, params);
        if (goodsList.size() == 0) {//需要获取写到数据库并且返回
            try {
                goodsList = HttpUtil.fetchGoods(steamId, type);
                if (goodsList.size() > 0) {
                    super.getHibernateTemplate().saveOrUpdateAll(goodsList);
                    return goodsList;
                }
                return null;
                //否则写数据库且返回
            } catch (Exception e) {
                logger.error("", e);
                return null;
            }
        } else {
            return goodsList;
        }
    }

}
