package com.accp.myoa.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;

import com.accp.myoa.entity.Goods;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

public class HttpUtil {
    public static void main(String[] args) throws UnsupportedEncodingException {
        try {
            List<Goods> list=fetchGoods("76561198089990093", "730");
            System.out.println();
        } catch (Exception e) {
           System.out.println(e);
        }

    }

    public static List<Goods> fetchGoods(String streamId,
                                         String type) throws Exception {
        List<Goods> goodsList = new ArrayList<>();
        String icon_url_preix = "http://steamcommunity-a.akamaihd.net/economy/image/";
        String jsondata = getJsonContent(
            "http://steamcommunity.com/profiles/" + streamId + "/inventory/json/" + type + "/2/");
        JSONObject jsonObj = JSONObject.parseObject(jsondata);
        //1、判断返回json数据是否为true,否则是链接不对或者其他原因导致取不到数据
        if (jsonObj != null && jsonObj.get("success").toString().trim().equals("true")) {
            Object jsonArray = jsonObj.get("rgDescriptions");
            LinkedHashMap<String, String> jsonMap = JSON.parseObject(jsonArray.toString(),
                new TypeReference<LinkedHashMap<String, String>>() {
                });
            for (Map.Entry<String, String> entry : jsonMap.entrySet()) {
                //保存键值
                JSONObject v = JSONObject.parseObject(entry.getValue());
                //组装查价格链接
                String priceUrl = "http://steamcommunity.com/market/priceoverview/?country=CN&currency=1&appid="
                                  + type + "&market_hash_name=";
                String hash_name_url = URLEncoder.encode(v.get("market_hash_name").toString(),
                    "utf-8"); //已经转空格为+了
                hash_name_url = hash_name_url.replace("+", "%20");
                priceUrl = priceUrl + hash_name_url;
                String pricedata = getJsonContent(priceUrl);
                JSONObject pricejson = JSONObject.parseObject(pricedata);
                //lower_price
                if (pricejson != null && pricejson.get("success").toString().trim().equals("true")) {
                    Goods g = new Goods();
                    g.setSteamId(streamId);
                    g.setType(type);
                    g.setName(v.get("name").toString());
                    if(pricejson.get("lowest_price")!=null)
                    {
                        g.setPrice(pricejson.get("lowest_price").toString()); 
                    }else if(pricejson.get("median_price")!=null){
                        g.setPrice(pricejson.get("median_price").toString());
                    }else{
                        g.setPrice(null);
                    }                                           
                    g.setIconBase64(readInputStream(new URL(icon_url_preix+v.get("icon_url")).openStream()));
                    goodsList.add(g);
                    System.out.println(
                        "name:" + v.get("name") + " lower_price:" + pricejson.get("lowest_price"));
                }
            }
        } else {
            return null;
        }
        return goodsList;
    }

    /**
     * 获取字节流
     * 
     * @param inputStream
     * @return
     * @throws Exception
     */
    public static String readInputStream(InputStream inputStream) throws Exception {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        outputStream.close();
        inputStream.close();
        byte[] encodeBase64 = Base64.encodeBase64(outputStream.toByteArray());  
        return new String(encodeBase64); 
    }

    public static String getJsonContent(String urlStr) {
        try {// 获取HttpURLConnection连接对象
            URL url = new URL(urlStr);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            // 设置连接属性
            httpConn.setConnectTimeout(3000);
            httpConn.setDoInput(true);
            httpConn.setRequestMethod("GET");
            // 获取相应码
            int respCode = httpConn.getResponseCode();
            if (respCode == 200) {
                return ConvertStream2Json(httpConn.getInputStream());
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }

    private static String ConvertStream2Json(InputStream inputStream) {
        String jsonStr = "";
        // ByteArrayOutputStream相当于内存输出流
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
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