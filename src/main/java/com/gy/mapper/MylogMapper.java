package com.gy.mapper;

import com.gy.entity.Mylog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/4/2 15:42
 */
public interface MylogMapper {
    @Insert("INSERT INTO T_MYLOG (id,operator,message,type,tyre_id,time) values(#{id},#{operator}," +
            "#{message},#{type},#{tyreId},sysdate)")
    public void addMyLog(Mylog mylog);
}
