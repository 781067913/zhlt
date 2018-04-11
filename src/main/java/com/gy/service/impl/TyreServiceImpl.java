package com.gy.service.impl;

import com.gy.entity.Mylog;
import com.gy.entity.Tyre;
import com.gy.entity.WorkOrder;
import com.gy.mapper.MylogMapper;
import com.gy.mapper.TyreMapper;
import com.gy.service.TyreService;
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
 * @Date: Created in 2018/3/30 11:02
 */
@Service
public class TyreServiceImpl implements TyreService {
    @Autowired
    private TyreMapper tyreMapper;

    @Autowired
    private MylogMapper mylogMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public ResultObject tyreRzk(Tyre tyre,String token) {
        ResultObject ro = new ResultObject();
        //根据token获取缓存中的用户id（id字段）
        String operator=redisUtil.getRedisUserId(token);
        if(EmptyUtils.isEmpty(operator)){//查看该token对应的用户是否存在，不存在则return，且提示500
            ro.setCode(Constant.RESULT_CODE_LOGIN);
            ro.setMessage(Constant.RESULT_MESSAGE_LOGIN);
            return ro;
        }

        tyre.setProcessStatus(Constant.TYRE_PROCESS_STATUS_RZK);

        if(tyreMapper.insertTyre(tyre)>0){
            ro.setCode(Constant.RESULT_CODE_SUCCESS);
            ro.setMessage(Constant.RESULT_MESSAGE_SUCCESS);


            //记录日记
            mylogMapper.addMyLog(MylogUtil.getMylog(new Date().getTime()+"",operator,Constant.MYLOG_MESSAGE_GET,
                    Constant.MYLOG_TYPE_GET,tyre.getId()));
        }else{
            ro.setCode(Constant.RESULT_CODE_ERROR);
            ro.setMessage(Constant.RESULT_MESSAGE_ERROR);
        }
        return ro;
    }

    @Override
    public ResultObject tyreRzkAll(List<Tyre> tyreList,String token) {
        ResultObject ro = new ResultObject();
        //根据token获取缓存中的用户id（id字段）
        String operator=redisUtil.getRedisUserId(token);
        if(EmptyUtils.isEmpty(operator)){//查看该token对应的用户是否存在，不存在则return，且提示500
            ro.setCode(Constant.RESULT_CODE_LOGIN);
            ro.setMessage(Constant.RESULT_MESSAGE_LOGIN);
            return ro;
        }


        if(tyreMapper.insertTyreAll(tyreList)>0){
            ro.setCode(Constant.RESULT_CODE_SUCCESS);
            ro.setMessage(Constant.RESULT_MESSAGE_SUCCESS);


            //记录日记
            mylogMapper.addMyLog(MylogUtil.getMylog(new Date().getTime()+"",operator,Constant.MYLOG_MESSAGE_GET,
                    Constant.MYLOG_TYPE_GET,tyreList.get(0).getId()));
        }else{
            ro.setCode(Constant.RESULT_CODE_ERROR);
            ro.setMessage(Constant.RESULT_MESSAGE_ERROR);
        }
        return ro;
    }

    @Override
    public List<Tyre> cartyrelist(String carNo) {
        return tyreMapper.findTyreList(carNo);
    }

    @Override
    public ResultObject install(String carNo, String installPlace, String id, String token) {
        ResultObject ro = new ResultObject();

        //根据token获取缓存中的用户id（id字段）
        String operator=redisUtil.getRedisUserId(token);
        if(EmptyUtils.isEmpty(operator)){//查看该token对应的用户是否存在，不存在则return，且提示500
            ro.setCode(Constant.RESULT_CODE_LOGIN);
            ro.setMessage(Constant.RESULT_MESSAGE_LOGIN);
            return ro;
        }

        //查看当前位置是否存在其他轮胎
        Tyre searchTyre = new Tyre();
        searchTyre.setCarNo(carNo);
        searchTyre.setInstallPlace(installPlace);
        List<Tyre> searchList = tyreMapper.findList(searchTyre);
        if(searchList.size()>0){
            Tyre t = searchList.get(0);
            ro.setCode(Constant.RESULT_CODE_FALSE);
            ro.setMessage("安装失败！该位置已经安装了轮胎："+t.getId()+",如要安装，请先卸载！");
            return ro;
        }

        //安装轮胎
        String processStatus =Constant.TYRE_PROCESS_STATUS_INSTALL;
        if(tyreMapper.install(carNo,installPlace,processStatus,id)>0){
            ro.setCode(Constant.RESULT_CODE_SUCCESS);
            ro.setMessage(Constant.RESULT_MESSAGE_SUCCESS);

            //记录日记
            mylogMapper.addMyLog(MylogUtil.getMylog(new Date().getTime() + "", operator, Constant.MYLOG_MESSAGE_INSTALL+":车牌号-"+carNo+"; 位置-"+installPlace,
                    Constant.MYLOG_TYPE_INSTALL, id));
        }else{
            ro.setCode(Constant.RESULT_CODE_ERROR);
            ro.setMessage(Constant.RESULT_MESSAGE_ERROR);
        }
        return ro;
    }

    @Override
    public Tyre getTyre(String id) {
        return tyreMapper.getTyre(id);
    }

    @Override
    public ResultObject uninstall(String carNo,String installPlace,String id,String status, String token) {
        ResultObject ro = new ResultObject();

        //根据token获取缓存中的用户id（id字段）
        String operator=redisUtil.getRedisUserId(token);
        if(EmptyUtils.isEmpty(operator)){//查看该token对应的用户是否存在，不存在则return，且提示500
            ro.setCode(Constant.RESULT_CODE_LOGIN);
            ro.setMessage(Constant.RESULT_MESSAGE_LOGIN);
            return ro;
        }

        //卸载轮胎
        String processStatus =Constant.TYRE_PROCESS_STATUS_WAIT;
        if(tyreMapper.uninstall(carNo,installPlace,processStatus,id,status)>0){
            ro.setCode(Constant.RESULT_CODE_SUCCESS);
            ro.setMessage(Constant.RESULT_MESSAGE_SUCCESS);

            //记录日记
            mylogMapper.addMyLog(MylogUtil.getMylog(new Date().getTime() + "", operator, Constant.MYLOG_MESSAGE_UNINSTALL+":车牌号-"+carNo+"; 位置-"+installPlace,
                    Constant.MYLOG_TYPE_UNINSTALL, id));
        }else{
            ro.setCode(Constant.RESULT_CODE_ERROR);
            ro.setMessage(Constant.RESULT_MESSAGE_ERROR);
        }
        return ro;
    }

    @Override
    public ResultObject getCheckInfo(String id) {
        ResultObject ro = new ResultObject();
        Tyre t = tyreMapper.getTyre(id);
        if(t==null){
            ro.setCode(Constant.RESULT_CODE_FALSE);
            ro.setMessage("该轮胎frid还未录入，请先入库");
        }else if(EmptyUtils.isEmpty(t.getCarNo())){
            ro.setCode(Constant.RESULT_CODE_FALSE);
            ro.setMessage("该轮胎还未安装，无法趟检，请先安装！");
        }else {
            ro.setCode(Constant.RESULT_CODE_SUCCESS);
            ro.setMessage(Constant.RESULT_MESSAGE_SUCCESS);
            ro.setData(t);
        }
        return ro;
    }

    @Override
    public ResultObject writeDepth(String id, double depth, String token) {
        ResultObject ro = new ResultObject();
        //根据token获取缓存中的用户id（id字段）
        String operator=redisUtil.getRedisUserId(token);
        if(EmptyUtils.isEmpty(operator)){//查看该token对应的用户是否存在，不存在则return，且提示500
            ro.setCode(Constant.RESULT_CODE_LOGIN);
            ro.setMessage(Constant.RESULT_MESSAGE_LOGIN);
            return ro;
        }

        if(tyreMapper.writeDepth(id, depth)>0){
            ro.setCode(Constant.RESULT_CODE_SUCCESS);
            ro.setMessage(Constant.RESULT_MESSAGE_SUCCESS);

            //记录日记
            mylogMapper.addMyLog(MylogUtil.getMylog(new Date().getTime()+"",operator,Constant.MYLOG_MESSAGE_CHECK_DEPTH+"--"+depth,
                    Constant.MYLOG_TYPE_CHECK_DEPTH,id));
        }else{
            ro.setCode(Constant.RESULT_CODE_ERROR);
            ro.setMessage(Constant.RESULT_MESSAGE_ERROR);
        }
        return ro;
    }

}
