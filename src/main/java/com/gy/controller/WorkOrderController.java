package com.gy.controller;

import com.gy.service.WorkOrderService;
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

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/4/9 9:46
 */
@RestController
@RequestMapping(value = "/workorder")
@Api(value = "/报工单workorder", description = "报工单操作相关接口")
@Slf4j
public class WorkOrderController {

    @Autowired
    private WorkOrderService workOrderService;

    @ApiOperation(value="趟检--报工单操作", notes="纹深输入后，在要生成的报工单页面，选择申报原因，")
    @PostMapping(value = "/add")
    public ResultObject addWorkOrder(@RequestParam(value = "tyreId") String tyreId, @RequestParam(value = "installPlace") String installPlace,
                                     @RequestParam(value = "carNo") String carNo,@RequestParam(value = "problem") String problem,
                                     @RequestParam("token") String token){

        //如果趟检的报工单的问题申报是合格的，则直接结束
        if(Constant.WORK_ORDER_PROBLEM_YES.equals(problem)){
            ResultObject ro = new ResultObject();
            ro.setMessage(Constant.RESULT_MESSAGE_SUCCESS);
            ro.setCode(Constant.RESULT_CODE_SUCCESS);
            return ro;
        }else{//否则，插入一条报工单，且修改轮胎状态
            return workOrderService.workOrder(tyreId, installPlace, carNo, problem, token);
        }
    }

    @ApiOperation(value="报工单列表", notes="纹深输入后，在要生成的报工单页面，选择申报原因，")
    @PostMapping(value = "/list")
    public ResultObject list(@RequestParam(value = "tyreId",required = false) String tyreId,
                             @RequestParam(value = "carNo",required = false) String carNo){

        return workOrderService.list(tyreId,carNo);
    }

    @ApiOperation(value="报工单处理", notes="选择报工单操作")
    @PostMapping(value = "/deal")
    public ResultObject deal(@RequestParam(value = "id") String id,@RequestParam(value = "result") String result,@RequestParam("token") String token){
        return workOrderService.deal(id, result,token);
    }
}
