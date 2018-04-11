package com.gy.service;

import com.gy.entity.Car;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/29 17:12
 */
@Service
public interface CarService {

    public List<Car> findCarByCarNo(String carNo);
}
