/**
 * University Of Chongqing.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.accp.myoa.task;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Random;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

/**
 * UpdateProxy
 * @author wanglei
 * @version $Id: UpdateProxy.java, v 0.1 2016年4月25日 下午8:46:31 wanglei Exp $
 */
public class UpdateProxy extends TimerTask {

    private String[]                            checkURL = new String[] { "http://t.sohu.com/",
                                                                          "http://www.baidu.com",
                                                                          "http://www.ifeng.com/",
                                                                          "http://www.qq.com/" };
    //保存获取到的有效的IP列表,每次确保有10个有效代理IP
    public static CopyOnWriteArrayList<String> ipList   = new CopyOnWriteArrayList<String>();

    private String construcURL(int num) {
        String url = "http://qsrdk.daili666api.com/ip/?tid=556332115885300&filter=on&delay=3&category=2&num="
                     + num;
        return url;
    }

    /** 
     * @see java.util.TimerTask#run()
     */
    public void run() {

        //每次重新跑之前先看看ipList里面还有多少有效的ip,只补充不足的部分,不需要全部重新去取
        if (ipList.size() > 0) {
            //测试是都有效,有的话继续保留,不是的remove掉
            for (String ip : ipList) {
                //无效,去掉
                if (!proxyChecker(ip)) {
                    ipList.remove(ip);
                }
            }
        }

        //看看数量够不够5个,不够就补充起来
        int num = 3 - ipList.size();
        getIPList(num, ipList);
        for (String l : ipList) {
            System.out.println("代理ip更新完毕:"+l);
        }

    }

    private CopyOnWriteArrayList<String> getIPList(int num, CopyOnWriteArrayList<String> list) {
        String str = null;
        try {
            URL url = new URL(construcURL(num));
            URLConnection uc = url.openConnection();
            uc.setReadTimeout(5000);
            uc.setConnectTimeout(5000);
            InputStream is = uc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            while ((str = br.readLine()) != null) {
                /*if(proxyChecker(str)){*/
                    list.add(str);
              /*  }    */         
            }
            br.close();  
           /* int needNum=num-list.size();
            if(needNum>0){
                getIPList(needNum, list);
            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> list = new UpdateProxy().getIPList(2,
            new CopyOnWriteArrayList<String>());
        System.out.println(list);
    }

    private boolean proxyChecker(String ip) {
        boolean flag = false;
        int statusCode = 0;
        int index=new Random().nextInt(checkURL.length);
        String check=checkURL[index];
        try {
            String host = ip.split(":")[0];
            int port = Integer.parseInt(ip.split(":")[1]);
            HttpClient httpClient = new HttpClient();
            httpClient.getHostConfiguration().setProxy(host, port);
            // 连接超时时间（默认3秒 3000ms） 单位毫秒（ms）
            int connectionTimeout = 5000;
            // 读取数据超时时间（默认5秒 5000ms） 单位毫秒（ms）
            int soTimeout = 5000;
            httpClient.getHttpConnectionManager().getParams()
                .setConnectionTimeout(connectionTimeout);
            httpClient.getHttpConnectionManager().getParams().setSoTimeout(soTimeout);
            HttpMethod method = new GetMethod(check);
            statusCode = httpClient.executeMethod(method);
            if (statusCode == 200) {
                flag = true;
            }
        } catch (Exception e) {
            return false;
        }
        return flag;
    }

}
