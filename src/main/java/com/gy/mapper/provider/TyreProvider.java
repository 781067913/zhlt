package com.gy.mapper.provider;

import com.gy.entity.Tyre;
import com.gy.util.EmptyUtils;
import org.apache.ibatis.jdbc.SQL;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/30 11:03
 */
public class TyreProvider {

    public String insertTyre(Tyre tyre){
        SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL

        sql.INSERT_INTO("T_TYRE");

        sql.VALUES("id", tyre.getId());
        if(EmptyUtils.isNotEmpty(tyre.getBatch())){ //判断blogId属性是否有值
            sql.VALUES("batch", tyre.getBatch());
        }

        if(EmptyUtils.isNotEmpty(tyre.getType())){ //判断blogId属性是否有值
            sql.VALUES("type", tyre.getType());
        }

        if(EmptyUtils.isNotEmpty(tyre.getSpec())){ //判断blogId属性是否有值
            sql.VALUES("spec", tyre.getSpec());
        }

        if(EmptyUtils.isNotEmpty(tyre.getBrand())){ //判断blogId属性是否有值
            sql.VALUES("brand", tyre.getBrand());
        }

        if(EmptyUtils.isNotEmpty(tyre.getStatus())){ //判断blogId属性是否有值
            sql.VALUES("status", tyre.getStatus());
        }

        sql.VALUES("process_status", tyre.getProcessStatus());
        sql.VALUES("get_time","sysdate");

        return sql.toString();
    }

    public String insertTyreAll(Map map){
        List<Tyre> tyres = (List<Tyre>) map.get("list");
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO T_TYRE ");
        sb.append("(id, batch,type,spec,brand,status,process_status,get_time) ");
        MessageFormat mf = new MessageFormat("#'{'list[{0}].id}, #'{'list[{0}].batch},#'{'list[{0}].type},#'{'list[{0}].spec}," +
                "#'{'list[{0}].brand},#'{'list[{0}].status},#'{'list[{0}].processStatus},sysdate FROM DUAL ");
        for (int i = 0; i < tyres.size(); i++) {
            sb.append(" select ");
            sb.append(mf.format(new Object[]{i}));
            if (i < tyres.size() - 1) {
                sb.append(" UNION ALL ");
            }
        }
        return sb.toString();
    }

    public String findList(Tyre tyre){
        StringBuffer sb = new StringBuffer(" SELECT * FROM t_tyre WHERE 1=1");
        if(EmptyUtils.isNotEmpty(tyre.getId())){
            sb.append(" and id=#{id}");
        }
        if(EmptyUtils.isNotEmpty(tyre.getCarNo())){
            sb.append(" and car_no=#{carNo}");
        }
        if(EmptyUtils.isNotEmpty(tyre.getInstallPlace())){
            sb.append(" and install_place=#{installPlace}");
        }
        if(EmptyUtils.isNotEmpty(tyre.getBatch())){
            sb.append(" and batch=#{batch}");
        }
        if(EmptyUtils.isNotEmpty(tyre.getStatus())){
            sb.append(" and status=#{status}");
        }
        if(EmptyUtils.isNotEmpty(tyre.getProcessStatus())){
            sb.append(" and process_status=#{processStatus}");
        }
        return sb.toString();
    }
}
