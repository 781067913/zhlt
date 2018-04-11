package com.gy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/29 17:13
 */
@Data
@ApiModel(description="车辆对象")
public class Car {

    private String id;

    @ApiModelProperty(example="车牌号",value="carNo")
    private String carNo;

    @ApiModelProperty(example="车辆类型",value="carType")
    private String carType;

    @ApiModelProperty(example="轮位类型",value="tyreType")
    private String tyreType;

    @ApiModelProperty(example="车辆状态",value="status")
    private String status;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(example="创建日期",value="createTime")
    private Date createTime;
}
