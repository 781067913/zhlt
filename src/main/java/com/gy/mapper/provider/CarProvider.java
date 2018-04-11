package com.gy.mapper.provider;

import com.gy.util.EmptyUtils;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.data.repository.query.Param;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/29 17:20
 */
public class CarProvider {

    public String findCarByCarNo(@Param("carNo") String carNo){
        StringBuffer sb = new StringBuffer(" SELECT ID,CAR_NO FROM T_CAR ");
        if(EmptyUtils.isNotEmpty(carNo)){
            sb.append(" where car_no like concat(concat('%',#{carNo}),'%')");
        }
        return sb.toString();
    }
}
