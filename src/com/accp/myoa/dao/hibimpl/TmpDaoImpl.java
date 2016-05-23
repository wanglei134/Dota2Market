/**
 * University Of Chongqing.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.accp.myoa.dao.hibimpl;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.accp.myoa.dao.TmpDao;
import com.accp.myoa.entity.Tmp;
import com.accp.myoa.entity.Userinfo;
import com.accp.myoa.util.HttpUtil;
import com.accp.myoa.util.RequestCache;

/**
 * TmpDaoImpl
 * @author wanglei
 * @version $Id: TmpDaoImpl.java, v 0.1 2016年4月17日 下午1:44:16 wanglei Exp $
 */
public class TmpDaoImpl extends BaseHibernateDao implements TmpDao {

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

    public List<Tmp> findByPage(String hql, int pageSize, int pageNum) {
        int firstResult = pageSize * (pageNum - 1);
        return super.findByPage(hql, firstResult, pageSize);
    }

    /** 
     * @see com.accp.myoa.dao.TmpDao#add(com.accp.myoa.entity.Tmp)
     */
    public void add(Tmp Tmp) {
        super.getHibernateTemplate().save(Tmp);
    }

    /** 
     * @see com.accp.myoa.dao.TmpDao#del(int)
     */
    public void del(int id) {
        super.getHibernateTemplate().delete(load(id));
    }

    /** 
     * @see com.accp.myoa.dao.TmpDao#update(com.accp.myoa.entity.Tmp)
     */
    public void update(Tmp Tmp) {
        getHibernateTemplate().update(Tmp);
    }

    /** 
     * @see com.accp.myoa.dao.TmpDao#load(int)
     */
    public Tmp load(int id) {
        return (Tmp) getHibernateTemplate().load(Tmp.class, id);
    }

    /** 
     * @see com.accp.myoa.dao.TmpDao#loadList(com.accp.myoa.entity.Tmp)
     */
    public List<Tmp> loadList(Tmp Tmp) {
        if (Tmp != null) {
            return getHibernateTemplate().findByExample(Tmp);
        }
        return getHibernateTemplate().loadAll(Tmp.class);
    }

    /** 
     * @see com.accp.myoa.dao.TmpDao#get(int)
     */
    public Tmp get(int id) {
        return (Tmp) getHibernateTemplate().get(Tmp.class, id);
    }

    private void deltmp() {
        String hql = "from Tmp where TIMESTAMPDIFF(SECOND,createtime,NOW())>=180";
        List<Tmp> tlist = getHibernateTemplate().find(hql);
        if (tlist.size() > 0) {
            long x = System.currentTimeMillis();
            System.out.println("开始删除tmp表");
            try {
                getHibernateTemplate().deleteAll(tlist);
            } catch (DataAccessException e) {
                System.out.println("删除数据出错!,跳过此次删除");
            }
            System.out.println("删除tmp表数据" + tlist.size() + "条");
            long y = System.currentTimeMillis();
            System.out.println("花费时间:" + (y - x));
        }
    }

    private static String filterContent(String content) {
        String flt = "'|and|exec|insert|select|delete|update|count|*|%"
                     + "|chr|mid|master|truncate|char|declare|; |or|-|+|,";
        String filter[] = flt.split("|");
        for (int i = 0; i < filter.length; i++) {
            content.replace(filter[i], "");
        }
        return content;
    }

    /** 
     * @see com.accp.myoa.dao.TmpDao#fetchData(java.lang.String, java.lang.String)
     */
    public String fetchData(String steamId, String type, String isGuoFuId, String ip, String city,
                            String browser) {

        if (steamId.contains("/id/")) {
            int index_a = steamId.indexOf("/id/");
            steamId = steamId.substring(index_a + 4);
            if (steamId.contains("/")) {
                steamId = steamId.split("/")[0];
            }
        } else if (steamId.contains("/profiles/")) {
            int index_a = steamId.indexOf("/profiles/");
            steamId = steamId.substring(index_a + 10);
            if (steamId.contains("/")) {
                steamId = steamId.split("/")[0];
            }
        }
        //过滤非法字符
        steamId = filterContent(steamId);
        if (steamId.trim().length() == 0) {
            return "输入不合法";
        }
        //判断是否是国服id
        if (isGuoFuId.equals("true")) {
            BigInteger bigInteger;
            try {
                bigInteger = new BigInteger(steamId);
            } catch (NumberFormatException e) {
                return "输入不合法";
            }
            bigInteger = bigInteger.add(new BigInteger("76561197960265728"));
            steamId = bigInteger.toString();
        }

        //添加用户浏览器,ip等信息
        Userinfo userinfo = new Userinfo();
        userinfo.setBrowser(browser);
        userinfo.setCity(city);
        userinfo.setIp(ip);
        userinfo.setSteamid(steamId);
        super.getHibernateTemplate().save(userinfo);

        //写之前判断缓存是不是有数据
        String keyForSearch = steamId + ":" + type;
        Object o = RequestCache.getRequestCahe().get(keyForSearch);
        while (o != null) {
            o = RequestCache.getRequestCahe().get(keyForSearch);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                logger.error("", e);
            }
            if (o == null)
                return "数据采集完毕";
        }

        //请求不为null写到缓存里面去
        RequestCache.getRequestCahe().put(keyForSearch, "111");
        try {
            //查之前同步删除数据
            Thread.sleep(500);
            deltmp();
            //3分钟内有数据直接返回
            long begintime = System.currentTimeMillis();
            String hql = "from Tmp where steamId='" + steamId + "' and type=" + type
                         + " and TIMESTAMPDIFF(MINUTE,createtime,NOW())<180";
            int hisCount = getCount(hql);
            if (hisCount > 0) {
                RequestCache.getRequestCahe().put(keyForSearch, null);
                return "数据采集完毕";
            }
            List<Tmp> TmpList = HttpUtil.fetchTmp(steamId, type, threadPoolTaskExecutor,
                super.getHibernateTemplate(), 0, 0);

            long endtime = System.currentTimeMillis();
            int x = (int) (endtime = begintime) / 1000;
            System.out.println("数据采集花费时间" + (endtime - begintime));
            hql = "from Tmp where steamId='" + steamId + "' and type=" + type
                  + " and TIMESTAMPDIFF(MINUTE,createtime,NOW())<" + (180 + x);
            hisCount = getCount(hql);
            if (hisCount > 0) {
                RequestCache.getRequestCahe().put(keyForSearch, null);
                return "数据采集完毕";
            }

            System.out.println("数据提取完毕" + type + ":" + steamId + " " + (new Date()));
            if (TmpList.size() > 0) {
                for (Tmp tmp : TmpList) {
                    super.getHibernateTemplate().save(tmp);
                }
                long z = System.currentTimeMillis();
                System.out.println("写数据花费时间：" + (z - endtime));
                System.out.println("数据写入完毕" + type + ":" + steamId + " " + (new Date()) + "数据大小:"
                                   + TmpList.size());
                RequestCache.getRequestCahe().put(keyForSearch, null);

            } else {
                RequestCache.getRequestCahe().put(keyForSearch, null);
                return "系统未查询到数据,请检查<br>" + "<ul>" + "<li>1、在个人资料中将库存设置为公开</li>"
                       + "<li>2、请检查输入的ID信息</li>" + "</ul>";
            }
            return "数据采集完毕";

        } catch (Exception e) {
            RequestCache.getRequestCahe().put(keyForSearch, null);
            return "系统未查询到数据,请检查<br>" + "<ul>" + "<li>1、在个人资料中将库存设置为公开</li>"
                   + "<li>2、请检查输入的ID信息</li>" + "</ul>";
        }
    }

    /** 
     * @see com.accp.myoa.dao.TmpDao#getPrice(java.lang.String)
     */
    public Object getPrice(String hql) {
        return super.getPrice(hql);
    }

}
