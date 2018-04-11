package com.gy.util;

import lombok.Data;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/23 11:07
 */
@Data
public class ResultObject {

    private Integer code;

    private Object data;

    private String message;
}
