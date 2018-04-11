package com.gy.service.impl;

import com.gy.entity.Mylog;
import com.gy.entity.WorkOrder;
import com.gy.mapper.MylogMapper;
import com.gy.mapper.TyreMapper;
import com.gy.mapper.WorkOrderMapper;
import com.gy.service.WorkOrderService;
import com.gy.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Date;
import java.util.List;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/4/9 9:48
 */
@Service
public class WorkOrderServiceImpl implements WorkOrderService{

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private WorkOrderMapper workOrderMapper;

    @Autowired
    private TyreMapper tyreMapper;

    @Autowired
    private MylogMapper mylogMapper;

    @Override
    @Transactional
    public ResultObject workOrder(String tyreId, String installPlace, String carNo, String problem, String token) {
        ResultObject ro = new ResultObject();
        //根据token获取缓存中的用户id（id字段）
        String operator=redisUtil.getRedisUserId(token);
        if(EmptyUtils.isEmpty(operator)){//查看该token对应的用户是否存在，不存在则return，且提示500
            ro.setCode(Constant.RESULT_CODE_LOGIN);
            ro.setMessage(Constant.RESULT_MESSAGE_LOGIN);
            return ro;
        }
        try {
            if(workOrderMapper.insertWorkOrder(new Date().getTime()+"",operator,tyreId,installPlace,carNo,problem)>0){
                if(tyreMapper.updateStatus(tyreId,Constant.TYRE_STATUS_YES)>0){
                    ro.setCode(Constant.RESULT_CODE_SUCCESS);
                    ro.setMessage(Constant.RESULT_MESSAGE_SUCCESS);

                    //记录日记
                    mylogMapper.addMyLog(MylogUtil.getMylog(new Date().getTime() + "", operator, Constant.MYLOG_MESSAGE_CHECK_WORKORDER,
                            Constant.MYLOG_TYPE_CHECK_WORKORDER, tyreId));
                }else {
                    ro.setCode(Constant.RESULT_CODE_ERROR);
                    ro.setMessage(Constant.RESULT_MESSAGE_ERROR);
                    TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                }
            }else{
                ro.setCode(Constant.RESULT_CODE_ERROR);
                ro.setMessage(Constant.RESULT_MESSAGE_ERROR);
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            ro.setMessage(Constant.RESULT_MESSAGE_ERROR);
            ro.setCode(Constant.RESULT_CODE_ERROR);
        }
        return ro;
    }

    @Override
    public ResultObject list(String tyreId, String carNo) {
        ResultObject ro = new ResultObject();
        WorkOrder wo = new WorkOrder();
        if(EmptyUtils.isNotEmpty(tyreId)){
            wo.setTyreId(tyreId);
        }
        if(EmptyUtils.isNotEmpty(carNo)) {
            wo.setCarNo(carNo);
        }
        List<WorkOrder> workOrderList = workOrderMapper.findList(wo);
        ro.setCode(Constant.RESULT_CODE_SUCCESS);
        ro.setMessage(Constant.RESULT_MESSAGE_SUCCESS);
        ro.setData(workOrderList);
        return ro;
    }

    @Override//记得还要修改轮胎的状态
    public ResultObject deal(String id, String result,String token) {
        ResultObject ro = new ResultObject();
        //根据token获取缓存中的用户id（id字段）
        String operator=redisUtil.getRedisUserId(token);
        if(EmptyUtils.isEmpty(operator)){//查看该token对应的用户是否存在，不存在则return，且提示500
            ro.setCode(Constant.RESULT_CODE_LOGIN);
            ro.setMessage(Constant.RESULT_MESSAGE_LOGIN);
            return ro;
        }

        if(workOrderMapper.updateResult(id,result)>0){
            ro.setCode(Constant.RESULT_CODE_SUCCESS);
            ro.setMessage(Constant.RESULT_MESSAGE_SUCCESS);

            //记录日记
            mylogMapper.addMyLog(MylogUtil.getMylog(new Date().getTime() + "", operator, Constant.MYLOG_MESSAGE_DEAL_WORKORDER,
                    Constant.MYLOG_TYPE_DEAL_WORKORDER, id));
        }else{
            ro.setCode(Constant.RESULT_CODE_ERROR);
            ro.setMessage(Constant.RESULT_MESSAGE_ERROR);
        }
        return ro;
    }

}
