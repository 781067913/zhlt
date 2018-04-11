package com.gy.service.impl;

import com.gy.entity.Car;
import com.gy.entity.Cspz;
import com.gy.mapper.CspzMapper;
import com.gy.service.CarService;
import com.gy.service.CspzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/30 14:26
 */
@Service
public class CspzServiceImpl implements CspzService {

    @Autowired
    private CspzMapper cspzMapper;

    @Override
    public List<Cspz> findList(Cspz cspz) {
        return cspzMapper.findList(cspz);
    }
}
