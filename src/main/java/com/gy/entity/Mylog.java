package com.gy.entity;

import lombok.Data;

import java.util.Date;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/4/2 14:56
 */
@Data
public class Mylog {
    private String id;

    private String operator;

    private String message;

    private String type;

    private String tyreId;

    private Date time;

}
