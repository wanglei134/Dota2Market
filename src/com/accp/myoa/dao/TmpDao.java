
package com.accp.myoa.dao;
import java.util.List;

import com.accp.myoa.entity.Tmp;

public interface TmpDao {
	public void add(Tmp Tmp);
	public void del(int id);
	public void update(Tmp Tmp);
	public Tmp load(int id);
	public List<Tmp> loadList(Tmp Tmp);
	public Tmp get(int id);
	
	public String fetchData(String steamId,String type,String isGuoFuId,String ip,String city,String browser);
    public List<Tmp> findByPage(String hql,int pageSize,int pageNum);
    public int getCount(final String hql) ;
    public Object getResult(final String hql) ;
    public Object getPrice(final String hql) ;
	
}



