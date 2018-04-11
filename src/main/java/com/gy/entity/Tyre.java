package com.gy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/30 10:04
 */
@Data
@ApiModel(description="轮胎对象tyre")
public class Tyre {

    private String id;

    @ApiModelProperty(value="type",example="轮胎种类")
    private String type;

    @ApiModelProperty(value="spec",example="轮胎规格")
    private String spec;

    @ApiModelProperty(value="batch",example="批次号")
    private String batch;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value="createTime",example="入总库日期")
    private Date getTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value="sendTime",example="分配日期")
    private Date sendTime;

    @ApiModelProperty(value="status",example="轮胎状态")
    private String status;

    @ApiModelProperty(value="processStatus",example="流程状态")
    private String processStatus;

    @ApiModelProperty(value="depth",example="当前纹深")
    private double depth;

    @ApiModelProperty(value="carNo",example="车牌号")
    private String carNo;

    @ApiModelProperty(value="installPlace",example="安装位置")
    private String installPlace;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value="installTime",example="安装日期")
    private Date installTime;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value="checkTime",example="趟检日期")
    private Date checkTime;

    @ApiModelProperty(value="mileage",example="里程数")
    private double mileage;

    @ApiModelProperty(example="品牌",value="brand")
    private String brand;
}
