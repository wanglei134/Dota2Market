
package com.accp.myoa.dao;
import java.util.List;

import com.accp.myoa.entity.Users;

public interface UsersDao {
	public void add(Users Users);
	public void del(int id);
	public void update(Users Users);
	public Users load(int id);
	public List<Users> loadList(Users Users);
	public Users get(int id);
	
    public List<Users> findByPage(String hql,int pageSize,int pageNum);
    public int getCount(final String hql) ;
    public Object getResult(final String hql) ;
	
}



