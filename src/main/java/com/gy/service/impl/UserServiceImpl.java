package com.gy.service.impl;

import com.gy.entity.User;
import com.gy.mapper.UserMapper;
import com.gy.service.UserService;
import com.gy.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/23 10:39
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByLoginNameAndPassword(String userId,String password) {
        String passwordStr = MD5Util.MD5Encode(userId+password,"utf-8",false);
        return userMapper.getUserByLoginNameAndPassword(userId,passwordStr);
    }

    @Override
    public List<User> findList() {
        return userMapper.findList();
    }
}
