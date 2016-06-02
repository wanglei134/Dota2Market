/**
 * University Of Chongqing.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.accp.myoa.util;

import java.io.UnsupportedEncodingException;

/**
 * 
 * @author wanglei
 * @version $Id: EncodeParse.java, v 0.1 2016年4月28日 下午3:02:04 wanglei Exp $
 */
public class EncodeParse {
    
    public static void main(String[] args) {
        try { 
            /*String str="\u8bf7\u70b9\u51fb\u9a8c\u8bc1\u7801\u5b8c\u6210\u53d1\u8d34";
            System.out.println(changeCharset(str, "utf-8"));
            System.out.println(checkSupportUTF8("\\xF0\\xA1\\x8B\\xBE\\xE5\\xA2"));*/
            String x="https://steamcommunity.com/tradeoffer/new/?partner=110535357&token=CAo0foYB".split("token")[1].split("=")[1];
            System.out.println(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean checkSupportUTF8(String str){
        for(int i=0;i<str.length();i++){
            int c=str.codePointAt(i);
            if (c<0x0000||c>0xffff) {
                return false;
            }
        }     
        return true;
    }
    /**
     * 字符串编码转换的实现方法
     * @param str  待转换编码的字符串
     * @param newCharset 目标编码
     * @return 
     * @throws UnsupportedEncodingException
     */
    public static String changeCharset(String str, String newCharset)
      throws UnsupportedEncodingException {
     if (str != null) {
      //用默认字符编码解码字符串。
      byte[] bs = str.getBytes();
      //用新的字符编码生成字符串
      return new String(bs, newCharset);
     }
     return null;
    }

}
