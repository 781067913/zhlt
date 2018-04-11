package com.gy.util;

import com.gy.entity.Mylog;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/4/2 15:55
 */
public class MylogUtil {

    public static Mylog getMylog(String id,String operator,String message,String type,String tyreId){
        Mylog mylog = new Mylog();
        mylog.setId(id);
        mylog.setOperator(operator);
        mylog.setMessage(message);
        mylog.setType(type);
        mylog.setTyreId(tyreId);
        return mylog;
    }
}
