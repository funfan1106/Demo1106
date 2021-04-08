package com.fjc.order.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @program: guli_parent
 * @description: 订单号工具类
 * @author: Mr.Fan
 * @create: 2021-03-27 19:37
 **/
public class OrderNoUtil {
    public static  String getOrderNo(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for(int i=0;i<3;i++){
            result +=random.nextInt(10);
        }
        return newDate + result;
    }

}
