package com.gy.mapper.provider;

import com.gy.entity.Cspz;
import com.gy.util.EmptyUtils;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/30 14:27
 */
public class CspzProvider {

    public String findList(Cspz cspz){
        StringBuffer sb = new StringBuffer(" SELECT id,type,csdm,csz,cssm FROM t_cspz where 1=1 ");
        if(EmptyUtils.isNotEmpty(cspz.getId())){
            sb.append(" and id=#{id}");
        }
        if(EmptyUtils.isNotEmpty(cspz.getType())){
            sb.append(" and type=#{type}");
        }
        return sb.toString();
    }
}
