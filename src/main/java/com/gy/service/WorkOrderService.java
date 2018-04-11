package com.gy.service;

import com.gy.util.ResultObject;
import org.springframework.stereotype.Service;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/4/9 9:47
 */
@Service
public interface WorkOrderService {
    public ResultObject workOrder(String id,String installPlace,String carNo,String problem,String token);

    public ResultObject list(String tyreId,String carNo);

    public ResultObject deal(String id,String result,String token);
}
