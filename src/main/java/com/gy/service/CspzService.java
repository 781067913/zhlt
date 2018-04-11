package com.gy.service;

import com.gy.entity.Cspz;
import com.gy.util.ResultObject;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/30 14:24
 */
@Service
public interface CspzService {

    public List<Cspz> findList(Cspz cspz);
}
