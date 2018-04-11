package com.gy.controller;

import com.gy.entity.Car;
import com.gy.entity.Tyre;
import com.gy.service.TyreService;
import com.gy.util.Constant;
import com.gy.util.EmptyUtils;
import com.gy.util.ResultObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/30 10:03
 */
@RestController
@RequestMapping(value = "/tyre")
@Api(value = "/轮胎tyre", description = "轮胎操作相关接口")
@Slf4j
public class TyreController {
    @Autowired
    private TyreService tyreService;

    @ApiOperation(value="车胎入总库", notes="输入车胎frid，批次号batch，车胎种类type，车胎规格spec，车胎是否合格status(1合格，0不合格)等信息")
    @PostMapping(value = "/tyrerzk")
    public ResultObject tyreRzk11(@RequestParam(value = "tyreId") String tyreId,@RequestParam(value = "batch") String batch,@RequestParam(value = "type") String type,@RequestParam(value = "spec") String spec,@RequestParam(value = "brand") String brand,@RequestParam(value = "status",required = false) String status,@RequestParam("token") String token){
        Tyre t = new Tyre();
        t.setId(tyreId);
        t.setBatch(batch);
        t.setType(type);
        t.setSpec(spec);
        t.setBrand(brand);
        if(EmptyUtils.isEmpty(status)){
            status="1";
        }
        t.setStatus(status);
        return tyreService.tyreRzk(t,token);
    }

    @ApiOperation(value="车胎入总库批量入库", notes="输入车胎frid，批次号batch，车胎种类type，车胎规格spec，车胎是否合格status(1合格，0不合格)等信息")
    @PostMapping(value = "/tyreRzkAll")
    public ResultObject tyreRzkAll(@RequestParam(value = "tyreIds") String[] tyreIds,@RequestParam(value = "batch") String batch,@RequestParam(value = "type") String type,@RequestParam(value = "spec") String spec,@RequestParam(value = "brand") String brand,@RequestParam(value = "status",required = false) String status,@RequestParam("token") String token){
       List<Tyre> tyreList= new ArrayList<Tyre>();
        for(int i=0;i<tyreIds.length;i++){
            Tyre t = new Tyre();
            t.setId(tyreIds[i]);
            t.setBatch(batch);
            t.setType(type);
            t.setSpec(spec);
            t.setBrand(brand);
            t.setStatus(Constant.TYRE_STATUS_YES);
            t.setProcessStatus(Constant.TYRE_PROCESS_STATUS_RZK);
            tyreList.add(t);
        }

        return tyreService.tyreRzkAll(tyreList,token);
    }

    @ApiOperation(value="获取安装的车的当前轮胎信息", notes="点击安装后，车辆俯视图上展示的车胎信息，有数据的且status为1的表示合格，status为0的表示不合格，无数据的轮胎位置为灰色")
    @PostMapping(value = "/cartyrelist")
    public ResultObject cartyrelist(@RequestParam(value = "carNo") String carNo){
        ResultObject ro = new ResultObject();
        List<Tyre> list = tyreService.cartyrelist(carNo);
        ro.setData(list);
        ro.setMessage(Constant.RESULT_MESSAGE_SUCCESS);
        ro.setCode(Constant.RESULT_CODE_SUCCESS);
        return ro;
    }

    @ApiOperation(value="安装轮胎到车子上", notes="扫码后，点击要安装的位置（位置为三位数字符串，第一位表示前桥1，中桥2，" +
            "后桥3，第二位表示该排有多少个轮胎，最后一位表示所在位置，如前桥左外，则是141，后桥右内则是343）")
    @PostMapping(value = "/install")
    public ResultObject install(@RequestParam(value = "carNo") String carNo,@RequestParam("installPlace") String installPlace,
                                @RequestParam("id") String id,@RequestParam("token") String token){
        return tyreService.install(carNo,installPlace,id,token);
    }

    @ApiOperation(value="卸载轮胎", notes="输入要拆卸的轮胎id,车牌号，位置，当前状态")
    @PostMapping(value = "/uninstall")
    public ResultObject uninstall(@RequestParam(value = "carNo") String carNo,@RequestParam("installPlace") String installPlace,
                                @RequestParam("id") String id,@RequestParam("status") String status,@RequestParam("token") String token){
        return tyreService.uninstall(carNo, installPlace, id, status, token);
    }

    @ApiOperation(value="获取车胎信息", notes="输入要获取的车胎信息的id")
    @PostMapping(value = "/gettyre")
    public ResultObject getTyre(@RequestParam("id") String id){
        ResultObject ro = new ResultObject();
        Tyre tyre = tyreService.getTyre(id);
        ro.setMessage(Constant.RESULT_MESSAGE_SUCCESS);
        ro.setCode(Constant.RESULT_CODE_SUCCESS);
        ro.setData(tyre);
        return ro;
    }

    @ApiOperation(value="趟检--获取基本信息", notes="输入要趟检的轮胎id，自动获取其所在车牌，轮胎当前位置和状态信息")
    @PostMapping(value = "/getcheckinfo")
    public ResultObject getCheckInfo(@RequestParam(value = "id") String id){
        return tyreService.getCheckInfo(id);
    }

    @ApiOperation(value="趟检--录入纹深", notes="输入要录入纹深的轮胎id，以及纹深depth（单位mm）")
    @PostMapping(value = "/writedepth")
    public ResultObject writeDepth(@RequestParam(value = "id") String id,@RequestParam(value = "depth") double depth,@RequestParam("token") String token){
        return tyreService.writeDepth(id, depth, token);
    }

}
