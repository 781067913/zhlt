package com.gy.controller;


import com.github.pagehelper.PageHelper;
import com.gy.entity.User;
import com.gy.service.UserService;
import com.gy.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/23 10:37
 */
@RestController
@RequestMapping(value = "/user")
@Api(value = "/用户controller", description = "用户操作相关接口")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value="用户登录", notes="输入用户名userId,密码password，实现登录功能，成功返回该用户信息和token，失败则为空," +
            "其中type为用户类型，1是管理员，可查看所有功能，2是安装拆卸人员，3是趟检人员，4是维护人员，拥有报工单功能")
/*    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })*/
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResultObject login(@RequestParam("userId") String userId,@RequestParam("password") String password,@RequestParam("token") String token){
        ResultObject ro = new ResultObject();
        log.info("操作:user/login ,登录用户：{};密码：{}",userId,password);
        try {
            User u =null;
            if(redisUtil.exists(Constant.REDIS_USER_KEY_HEAD+token)){
                Map userMap = redisUtil.hmget(Constant.REDIS_USER_KEY_HEAD+token);
                u = Bean2MapUtil.toBean(User.class,userMap);
            }else {
                u=userService.getUserByLoginNameAndPassword(userId,password);
                if(EmptyUtils.isEmpty(u)){
                    ro.setCode(Constant.RESULT_CODE_FALSE);
                    ro.setMessage("账号或密码错误，请重新输入");
                    log.warn("操作：user/login; 账号或密码错误，登录失败");
                    return ro;
                }
                token= UuidUtil.getUUId_16();
                redisUtil.hmset(Constant.REDIS_USER_KEY_HEAD+token,Bean2MapUtil.toMap(u));
            }

            if(EmptyUtils.isNotEmpty(u)){
                Map resultMap = new HashMap();
                ro.setCode(Constant.RESULT_CODE_SUCCESS);
                ro.setMessage(Constant.RESULT_MESSAGE_SUCCESS);
                resultMap.put("user", u);
                resultMap.put("token", token);
                ro.setData(resultMap);
            }
        }catch (Exception e){
            e.printStackTrace();
            ro.setCode(Constant.RESULT_CODE_ERROR);
            ro.setMessage(Constant.RESULT_MESSAGE_ERROR);
            log.error("操作：user/login; 登录报错!");
        }finally {
            return ro;
        }
    }

    @ApiOperation(value="查询用户列表", notes="输入当前页pageNum，和每页大小pageSize，获取每页的用户列表")
/*    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "用户名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String")
    })*/
    @RequestMapping(value = "/findlist",method = RequestMethod.POST)
    public ResultObject findList(@RequestParam("pageNum") int pageNum,@RequestParam("pageSize") int pageSize){
        ResultObject ro = new ResultObject();
        PageHelper.startPage(pageNum, pageSize);
        List<User> list=userService.findList();
        ro.setCode(Constant.RESULT_CODE_SUCCESS);
        ro.setMessage(Constant.RESULT_MESSAGE_SUCCESS);
        ro.setData(list);
        return ro;
    }
}
