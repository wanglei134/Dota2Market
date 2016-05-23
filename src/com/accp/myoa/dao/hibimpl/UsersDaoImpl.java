/**
 * University Of Chongqing.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.accp.myoa.dao.hibimpl;

import java.util.List;

import com.accp.myoa.dao.UsersDao;
import com.accp.myoa.entity.Users;

/**
 * 
 * @author wanglei
 * @version $Id: UsersDaoImpl.java, v 0.1 2016年5月2日 上午1:21:57 wanglei Exp $
 */
public class UsersDaoImpl extends BaseHibernateDao implements UsersDao {

    /** 
     * @see com.accp.myoa.dao.UsersDao#add(com.accp.myoa.entity.Users)
     */
    public void add(Users Users) {
        super.getHibernateTemplate().save(Users);
    }

    /** 
     * @see com.accp.myoa.dao.UsersDao#del(int)
     */
    public void del(int id) {
        super.getHibernateTemplate().delete(load(id));
    }

    /** 
     * @see com.accp.myoa.dao.UsersDao#update(com.accp.myoa.entity.Users)
     */
    public void update(Users Users) {
        super.getHibernateTemplate().update(Users);
    }

    /** 
     * @see com.accp.myoa.dao.UsersDao#load(int)
     */
    public Users load(int id) {
        return (Users) super.getHibernateTemplate().load(Users.class, id);
    }

    /** 
     * @see com.accp.myoa.dao.UsersDao#loadList(com.accp.myoa.entity.Users)
     */
    public List<Users> loadList(Users Users) {
        return super.getHibernateTemplate().loadAll(Users.getClass());
    }

    /** 
     * @see com.accp.myoa.dao.UsersDao#get(int)
     */
    public Users get(int id) {
        return (Users) super.getHibernateTemplate().get(Users.class, id);
    }

    /** 
     * @see com.accp.myoa.dao.UsersDao#findByPage(java.lang.String, int, int)
     */
    public List<Users> findByPage(String hql, int pageSize, int pageNum) {
        int firstResult = pageSize * (pageNum - 1);
        return super.findByPage(hql, firstResult, pageSize);
    }

    /** 
     * @see com.accp.myoa.dao.UsersDao#getCount(java.lang.String)
     */
    public int getCount(String hql) {
        return super.getCount(hql);
    }

    /** 
     * @see com.accp.myoa.dao.UsersDao#getResult(java.lang.String)
     */
    public Object getResult(String hql) {
        return super.getResult(hql);
    }

}
