
package com.accp.myoa.dao;
import java.util.List;

import com.accp.myoa.entity.Goods;

public interface GoodsDao {
	public void add(Goods goods);
	public void del(int id);
	public void update(Goods goods);
	public Goods load(int id);
	public List<Goods> loadList(Goods goods);
	public Goods get(int id);
	
	public List<Goods> fetchData(String steamId,String type);
	
}



