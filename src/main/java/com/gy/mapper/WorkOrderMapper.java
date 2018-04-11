package com.gy.mapper;

import com.gy.entity.WorkOrder;
import com.gy.mapper.provider.TyreProvider;
import com.gy.mapper.provider.WorkOrderProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/4/9 9:51
 */
public interface WorkOrderMapper {

    @Insert("INSERT INTO t_work_order (id,operator,create_time,tyre_id,install_place,car_no,problem) values （#{id},#{operator},sysdate,#{tyreId},#{installPlace},#{carNo},#{problem}）")
    public int insertWorkOrder(@Param("id") String id,@Param("operator") String operator,@Param("tyreId") String tyreId,@Param("installPlace") String installPlace,@Param("carNo") String carNo,@Param("problem") String problem);

    @SelectProvider(type = WorkOrderProvider.class,method = "list")
    @Results({
            @Result(property = "installPlace", column = "install_place"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "tyreId", column = "tyre_id"),
            @Result(property = "carNo", column = "car_no")
    })
    public List<WorkOrder> findList(WorkOrder wo);

    @Update(" UPDATE t_work_order SET result=#{status} " +
            " WHERE id=#{id} ")
    public int updateResult(@Param("id") String id,@Param("result") String result);
}
