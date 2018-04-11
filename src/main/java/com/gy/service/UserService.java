package com.gy.service;

import com.gy.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: liumin
 * @Description:
 * @Date: Created in 2018/3/23 10:38
 */
@Service
public interface UserService {

    public User getUserByLoginNameAndPassword(String userId, String password);

    public List<User> findList();
}
