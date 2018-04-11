package com.gy.mapper;

import com.gy.entity.Cspz;
import com.gy.mapper.provider.CspzProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/30 14:27
 */
public interface CspzMapper {

    @SelectProvider(type = CspzProvider.class,method = "findList")
    public List<Cspz> findList(Cspz cspz);
}
