/**
 * University Of Chongqing.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.accp.myoa.service.impl;

import java.util.List;

import com.accp.myoa.dao.UsersDao;
import com.accp.myoa.entity.Users;
import com.accp.myoa.service.UsersService;

/**
 * 
 * @author wanglei
 * @version $Id: UsersServiceImpl.java, v 0.1 2016年5月2日 上午1:26:53 wanglei Exp $
 */
public class UsersServiceImpl implements UsersService {
    private UsersDao usersDao;

    /**
     * Setter method for property <tt>usersDao</tt>.
     * 
     * @param usersDao value to be assigned to property usersDao
     */
    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    /** 
     * @see com.accp.myoa.service.UsersService#add(com.accp.myoa.entity.Users)
     */
    public void add(Users Users) {
        usersDao.add(Users);
    }

    /** 
     * @see com.accp.myoa.service.UsersService#del(int)
     */
    public void del(int id) {
        usersDao.del(id);
    }

    /** 
     * @see com.accp.myoa.service.UsersService#update(com.accp.myoa.entity.Users)
     */
    public void update(Users Users) {
        usersDao.update(Users);
    }

    /** 
     * @see com.accp.myoa.service.UsersService#load(int)
     */
    public Users load(int id) {
        return usersDao.load(id);
    }

    /** 
     * @see com.accp.myoa.service.UsersService#loadList(com.accp.myoa.entity.Users)
     */
    public List<Users> loadList(Users Users) {
        return usersDao.loadList(Users);
    }

    /** 
     * @see com.accp.myoa.service.UsersService#get(int)
     */
    public Users get(int id) {
        return usersDao.get(id);
    }

    /** 
     * @see com.accp.myoa.service.UsersService#findByPage(java.lang.String, int, int)
     */
    public List<Users> findByPage(String hql, int pageSize, int pageNum) {
        return usersDao.findByPage(hql, pageSize, pageNum);
    }

    /** 
     * @see com.accp.myoa.service.UsersService#getCount(java.lang.String)
     */
    public int getCount(String hql) {
        return usersDao.getCount(hql);
    }

    /** 
     * @see com.accp.myoa.service.UsersService#getResult(java.lang.String)
     */
    public Object getResult(String hql) {
        return usersDao.getResult(hql);
    }

}
