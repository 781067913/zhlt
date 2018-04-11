package com.gy.mapper;

import com.gy.entity.Tyre;
import com.gy.mapper.provider.TyreProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/30 11:02
 */
public interface TyreMapper {

    @InsertProvider(type = TyreProvider.class,method = "insertTyre")
    public int insertTyre(Tyre tyre);

    @InsertProvider(type = TyreProvider.class,method = "insertTyreAll")
    public int insertTyreAll(@Param("list") List<Tyre> list);

    @Select(" SELECT id，status,install_place,car_no From t_tyre where car_no=#{carNo} order by install_place")
    @Results({
            @Result(property = "installPlace", column = "install_place"),
            @Result(property = "carNo", column = "car_no")
    })
    public List<Tyre> findTyreList(@Param("carNo") String carNo);

    @Update(" UPDATE t_tyre SET car_no=#{carNo}, install_place=#{installPlace},process_status=#{processStatus},install_time=sysdate WHERE id=#{id}")
    public int install(@Param("carNo") String carNo,@Param("installPlace") String installPlace,@Param("processStatus") String processStatus,@Param("id") String id);

    @Update(" UPDATE t_tyre SET car_no=null, install_place=null,process_status=#{processStatus}" +
            " WHERE id=#{id} and car_no=#{carNo} and install_place=#{installPlace} and status=#{status}")
    public int uninstall(@Param("carNo") String carNo,@Param("installPlace") String installPlace,@Param("processStatus") String processStatus,@Param("id") String id,@Param("status") String status);


    @SelectProvider(type = TyreProvider.class,method = "findList")
    @Results({
            @Result(property = "installPlace", column = "install_place"),
            @Result(property = "processStatus", column = "process_status"),
            @Result(property = "carNo", column = "car_no")
    })
    public List<Tyre> findList(Tyre tyre);

    @Select(" SELECT id，status,install_place,car_no From t_tyre where id=#{id}")
    @Results({
            @Result(property = "installPlace", column = "install_place"),
            @Result(property = "carNo", column = "car_no")
    })
    public Tyre getTyre(@Param("id") String id);

    @Update(" UPDATE t_tyre SET depth=#{depth}, check_time=sysdate" +
            " WHERE id=#{id} and car_no is not null ")
    public int writeDepth(@Param("id") String id,@Param("depth") double depth);

    @Update(" UPDATE t_tyre SET status=#{status} " +
            " WHERE id=#{tyreId} ")
    public int updateStatus(@Param("tyreId") String tyreId,@Param("status") String status);
}
