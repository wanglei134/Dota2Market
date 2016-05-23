/**
 * University Of Chongqing.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.accp.myoa.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.Map;

import com.accp.myoa.entity.Users;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

/**
 * 
 * @author wanglei
 * @version $Id: GetUsersInfo.java, v 0.1 2016年5月2日 上午1:32:32 wanglei Exp $
 */
public class GetUsersInfo {

    public static void main(String[] args) {
        getInfo("http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=7179C3828113D400D1B6BA9B1CF852F3&steamids=76561198070801085");
    }
    public static Users getInfo(String url){
        Users u=new Users();
        try {
            String jsonStr=getJsonContent(url);
            JSONObject jsonObj = JSONObject.parseObject(jsonStr);
            if(jsonObj!=null){
                Object jsonArray = jsonObj.get("response");
                LinkedHashMap<String, String> jsonMap = JSON.parseObject(jsonArray.toString(),
                    new TypeReference<LinkedHashMap<String, String>>() {
                    });
                for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
                    //保存键值  
                   u= JSON.parseObject(entry.getValue().replace("[", "").replace("]", ""),Users.class);                   
                }
               
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
    

    /**
     * 1、返回字符串
     * 2、返回inputstream
     * 
     * @param urlStr
     * @param type
     * @return
     * @throws Exception
     */
    public static String getJsonContent(String urlStr) throws Exception {

        URL url = new URL(urlStr);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        // 设置连接属性   
        httpConn.setReadTimeout(3000);
        httpConn.setDoInput(true);
        httpConn.setRequestMethod("GET");
        // 获取相应码
        int respCode = httpConn.getResponseCode();
        if (respCode == 200) {
            return ConvertStream2Json(httpConn.getInputStream());
        }
        System.out.println("http异常码:" + respCode);
        return "http:" + respCode;
    }

    private static String ConvertStream2Json(InputStream inputStream) {
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[512];
        int len = 0;
        // 将输入流转移到内存输出流中
        try {
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, len);
            }
            // 将内存流转换为字符串
            jsonStr = new String(out.toByteArray());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonStr;
    }
}
