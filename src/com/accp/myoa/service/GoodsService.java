
package com.accp.myoa.service;

import java.util.List;

import com.accp.myoa.entity.Goods;

public interface GoodsService {
	public void add(Goods goods);
	public void del(int id);
	public void update(Goods goods);
	public Goods load(int id);
	public List<Goods> loadList(Goods goods);
	public Goods get(int id);
	
	//添加一个方法
	public String fetchData(String steamId,String type);
	public List<Goods> findByPage(String hql,int pageSize,int pageNum);
	public int getCount(final String hql) ;
	public Object getResult(final String hql) ;
}



