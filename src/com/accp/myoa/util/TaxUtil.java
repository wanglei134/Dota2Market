/**
 * University Of Chongqing.com Inc.
 * Copyright (c) 2004-2016 All Rights Reserved.
 */
package com.accp.myoa.util;

import java.text.DecimalFormat;

/**
 * TaxUtil
 * @author wanglei
 * @version $Id: TaxUtil.java, v 0.1 2016年5月10日 下午1:30:13 wanglei Exp $
 */
public class TaxUtil {

    public static void main(String[] args) {
        System.out.println(new DecimalFormat("0.00").format(TaxUtil.calcAfterTaxPrice("0.22")));
    }
    public static double calcAfterTaxPrice(String price) {
        double saleprice = Double.parseDouble(price);
        double dota2fee = Math.floor(saleprice * 1000 * 2 / 23 / 10);
        if (dota2fee <= 1) {
            dota2fee = 0.01;
        } else {
            dota2fee = dota2fee / 100;
        }
        double vfee = Math.floor(saleprice * 1000 / 23 / 10);
        if (vfee <= 1) {
            vfee = 0.01;
        } else {
            vfee = vfee / 100;
        }
        saleprice = saleprice - vfee - dota2fee;

        return saleprice;
    }
    
 }
