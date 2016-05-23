/**
 * University Of Chongqing.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.accp.myoa.wcf;

/**
 * 
 * @author wanglei
 * @version $Id: Test.java, v 0.1 2016年5月8日 上午1:53:32 wanglei Exp $
 */
public class Test {

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {

        String a="http://steamcommunity.com/profiles/76561198089990093";
        if(a.contains("/id/")){
            int index_a=a.indexOf("/id/");
            a=a.substring(index_a+4);
            if(a.contains("/")){
                a=a.split("/")[0];
            }
            System.out.println(a);
        }else if(a.contains("/profiles/")){
            int index_a=a.indexOf("/profiles/");
            a=a.substring(index_a+10);
            if(a.contains("/")){
                a=a.split("/")[0];
            }
        }
    }

}
