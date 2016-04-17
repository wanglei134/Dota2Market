
package com.accp.myoa.service.impl;

import java.util.List;

import com.accp.myoa.dao.GoodsDao;
import com.accp.myoa.entity.Goods;
import com.accp.myoa.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {
	private GoodsDao goodsDao;
		
	
	public void setGoodsDao(GoodsDao goodsDao) {
        this.goodsDao = goodsDao;
    }

    public void add(Goods goods) {
		goodsDao.add(goods);

	}

	public void del(int id) {
		goodsDao.del(id);

	}

	public Goods load(int id) {
		return goodsDao.load(id);
	}

	public List<Goods> loadList(Goods goods) {
		return goodsDao.loadList(goods);
	}

	public void update(Goods goods) {
		goodsDao.update(goods);

	}

    /** 
     * @see com.accp.myoa.service.GoodsService#get(int)
     */
    public Goods get(int id) {
        return goodsDao.get(id);
    }

    /** 
     * @see com.accp.myoa.service.GoodsService#fetchData(java.lang.String, java.lang.String)
     */
    public List<Goods> fetchData(String steamId, String type) {
        return goodsDao.fetchData(steamId, type);
    }

}

