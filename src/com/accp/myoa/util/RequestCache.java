/**
 * University Of Chongqing.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.accp.myoa.util;

import java.util.HashMap;
import java.util.Map;

/**
 * RequestCache
 * @author wanglei
 * @version $Id: RequestCache.java, v 0.1 2016年5月6日 上午2:57:22 wanglei Exp $
 */
public class RequestCache {
    private static Map<String, Object> requestCahe=new HashMap<String, Object>();
    /**
     * Setter method for property <tt>requestCahe</tt>.
     * 
     * @param requestCahe value to be assigned to property requestCahe
     */
    public static void setRequestCahe(Map<String, Object> requestCahe) {
        RequestCache.requestCahe = requestCahe;
    }
    /**
     * Getter method for property <tt>requestCahe</tt>.
     * 
     * @return property value of requestCahe
     */
    public static Map<String, Object> getRequestCahe() {
        return requestCahe;
    }
   

}
