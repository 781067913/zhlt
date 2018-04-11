package com.gy.util;

import java.util.UUID;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/29 16:01
 */
public class UuidUtil {
    public static String getUUId_16() {
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        // 0 代表前面补充0
        // 4 代表长度为4
        // d 代表参数为正数型
        return String.format("%015d", hashCodeV);
    }
}
