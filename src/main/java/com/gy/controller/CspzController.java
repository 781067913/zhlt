package com.gy.controller;

import com.gy.entity.Cspz;
import com.gy.service.CspzService;
import com.gy.util.Constant;
import com.gy.util.ResultObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/30 14:52
 */
@RestController
@RequestMapping(value = "/cspz")
@Api(value = "/参数cspz", description = "参数配置操作相关接口")
@Slf4j
public class CspzController {

    @Autowired
    private CspzService cspzService;

    @ApiOperation(value="配置列表", notes="id查询单个数据，type则查询该类数据，轮胎种类为：TYRE_TYPE，轮胎规格为：TYRE_SPEC")
    @PostMapping(value = "/list")
    public ResultObject findList(@RequestBody Cspz cspz){
        ResultObject ro = new ResultObject();
        List<Cspz> list = cspzService.findList(cspz);
        ro.setMessage(Constant.RESULT_MESSAGE_SUCCESS);
        ro.setCode(Constant.RESULT_CODE_SUCCESS);
        ro.setData(list);
        return ro;
    }
}
