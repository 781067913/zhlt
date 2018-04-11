package com.gy.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/4/8 16:21
 */
@Data
@ApiModel(description="报工单user")
public class WorkOrder {

    private String id;

    private String operator;

    private String createTime;

    private String tyreId;

    private String carNo;

    private String installPlace;

    private String problem;

    private String result;
}
