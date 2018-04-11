package com.gy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/23 10:40
 */
@Data
@ApiModel(description="用户对象user")
public class User implements Serializable{

    private static final long serialVersionUID = -3946734305303957850L;

    private String id;

    @ApiModelProperty(example="用户名",value="userId",required=true)
    private String userId;

    @ApiModelProperty(example="密码",value="password",required=true)
    private String password;

    @ApiModelProperty(example="昵称",value="userName")
    private String userName;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value="createTime",example="创建日期")
    private Date createTime;

    @ApiModelProperty(example="角色类型",value="1-超级管理员，可以查看所有功能；2-安装拆卸人员；3-趟检人员；4-维护人员")
    private String type;

}
