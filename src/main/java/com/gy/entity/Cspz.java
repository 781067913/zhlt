package com.gy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/30 11:52
 */
@Data
@ApiModel(description="参数配置")
public class Cspz {
    private String id;

    @ApiModelProperty(value="type",example="参数类型")
    private String type;

    @ApiModelProperty(value="csdm",example="参数代码")
    private String csdm;

    @ApiModelProperty(value="csz",example="参数值")
    private String csz;

    @ApiModelProperty(value="cssm",example="参数说明")
    private String cssm;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value="createTime",example="生成时间")
    private Date createTime;
}
