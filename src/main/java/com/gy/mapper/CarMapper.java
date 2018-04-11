package com.gy.mapper;

import com.gy.entity.Car;
import com.gy.mapper.provider.CarProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/29 17:19
 */
public interface CarMapper {

    @SelectProvider(type = CarProvider.class,method = "findCarByCarNo")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "carNo", column = "car_no")
    })
    public List<Car> findCarByCarNo(@Param("carNo") String carNo);
}
