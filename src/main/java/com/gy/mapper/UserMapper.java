package com.gy.mapper;

import com.gy.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/23 11:03
 */
public interface UserMapper {

    @Select("SELECT id,user_id,user_name,role_id FROM T_WX_USER " +
            "where user_id =#{user_id} and password=#{password} ")
    @Results({
            @Result(property = "userId", column = "user_id"),
            @Result(property = "userName", column = "user_name"),
            @Result(property = "createTime", column = "create_time"),
            @Result(column="role_id",property="type",
                    one=@One(select="com.gy.mapper.UserMapper.getRoleType"))
        })
    User getUserByLoginNameAndPassword(@Param("user_id") String user_id, @Param("password") String password);

    @Select("SELECT id,login_name,nick_name,password,create_time,change_time,status FROM T_USER ")
    @Results({
            @Result(property = "loginName", column = "login_name"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "changeTime", column = "change_time")
    })
    List<User> findList();

    @Select("SELECT type FROM T_ROLE where id=#{role_id}")
    String getRoleType(@Param("role_id") String role_id);
}
