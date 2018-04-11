package com.gy.controller;

import com.gy.entity.Car;
import com.gy.service.CarService;
import com.gy.util.Constant;
import com.gy.util.ResultObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/29 16:48
 */
@RestController
@RequestMapping(value = "/car")
@Api(value = "/车辆car", description = "车辆操作相关接口")
@Slf4j
public class CarController {

    @Autowired
    private CarService carService;

    @ApiOperation(value="获取车牌号", notes="输入车牌号carNo，返回车牌号信息")
    @PostMapping(value = "/list")
    public ResultObject findCarByCarNo(@RequestParam(value = "carNo",required = false) String carNo){
        ResultObject ro = new ResultObject();
        List<Car> list = carService.findCarByCarNo(carNo);
        ro.setData(list);
        ro.setMessage(Constant.RESULT_MESSAGE_SUCCESS);
        ro.setCode(Constant.RESULT_CODE_SUCCESS);
        return ro;
    }
}
