package com.gy.mapper.provider;

import com.gy.entity.WorkOrder;
import com.gy.util.EmptyUtils;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/4/9 11:26
 */
public class WorkOrderProvider {
    public String list(WorkOrder workOrder){
        StringBuffer sb = new StringBuffer(" SELECT * FROM t_work_order WHERE 1=1");
        if(EmptyUtils.isNotEmpty(workOrder.getId())){
            sb.append(" and id=#{id}");
        }
        if(EmptyUtils.isNotEmpty(workOrder.getCarNo())){
            sb.append(" and car_no=#{carNo}");
        }
        if(EmptyUtils.isNotEmpty(workOrder.getInstallPlace())){
            sb.append(" and install_place=#{installPlace}");
        }
        if(EmptyUtils.isNotEmpty(workOrder.getTyreId())){
            sb.append(" and tyre_id=#{tyreId}");
        }
        return sb.toString();
    }
}
