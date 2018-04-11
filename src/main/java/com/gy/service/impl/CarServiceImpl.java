package com.gy.service.impl;

import com.gy.entity.Car;
import com.gy.mapper.CarMapper;
import com.gy.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/29 17:13
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarMapper carMapper;

    @Override
    public List<Car> findCarByCarNo(String carNo) {
        return carMapper.findCarByCarNo(carNo);
    }
}
