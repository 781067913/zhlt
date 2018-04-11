package com.gy.service;

import com.gy.entity.Tyre;
import com.gy.util.ResultObject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/30 11:00
 */
@Service
public interface TyreService {

    public ResultObject tyreRzk(Tyre tyre,String token);

    public ResultObject tyreRzkAll(List<Tyre> tyreList,String token);

    public List<Tyre> cartyrelist(String carNo);

    public ResultObject install(String carNo,String installPlace,String id,String token);

    public ResultObject uninstall(String carNo,String installPlace,String id,String status,String token);

    public Tyre getTyre(String id);

    public ResultObject getCheckInfo(String id);

    public ResultObject writeDepth(String id,double depth,String token);

}
