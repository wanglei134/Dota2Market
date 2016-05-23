
package com.accp.myoa.service.impl;

import java.util.List;

import com.accp.myoa.dao.TmpDao;
import com.accp.myoa.entity.Tmp;
import com.accp.myoa.service.TmpService;

public class TmpServiceImpl implements TmpService {
	private TmpDao TmpDao;
		
	
	public void setTmpDao(TmpDao TmpDao) {
        this.TmpDao = TmpDao;
    }

    public void add(Tmp Tmp) {
		TmpDao.add(Tmp);

	}

	public void del(int id) {
		TmpDao.del(id);

	}

	public Tmp load(int id) {
		return TmpDao.load(id);
	}

	public List<Tmp> loadList(Tmp Tmp) {
		return TmpDao.loadList(Tmp);
	}

	public void update(Tmp Tmp) {
		TmpDao.update(Tmp);

	}

    /** 
     * @see com.accp.myoa.service.TmpService#get(int)
     */
    public Tmp get(int id) {
        return TmpDao.get(id);
    }

    /** 
     * @see com.accp.myoa.service.TmpService#fetchData(java.lang.String, java.lang.String)
     */
    public String fetchData(String steamId, String type,String isGuoFuId,String ip,String city,String browser) {
        return TmpDao.fetchData(steamId, type, isGuoFuId, ip, city, browser);
    }

    /** 
     * @see com.accp.myoa.service.TmpService#findByPage(java.lang.String, int, int)
     */
    public List<Tmp> findByPage(String hql, int pageSize, int pageNum) {
        return TmpDao.findByPage(hql, pageSize, pageNum);
    }

    /** 
     * @see com.accp.myoa.service.TmpService#getCount(java.lang.String)
     */
    public int getCount(String hql) {
        return TmpDao.getCount(hql);
    }

    /** 
     * @see com.accp.myoa.service.TmpService#getResult(java.lang.String)
     */
    public Object getResult(String hql) {
        return TmpDao.getResult(hql);
    }

    /** 
     * @see com.accp.myoa.service.TmpService#getPrice(java.lang.String)
     */
    public Object getPrice(String hql) {
        return TmpDao.getPrice(hql);
    }

}

