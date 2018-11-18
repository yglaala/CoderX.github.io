package com.bmsoft.canteensystem.service.impl;

import com.bmsoft.canteensystem.mapper.UserMapper;
import com.bmsoft.canteensystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public String getPic(Integer userId) {
        return userMapper.getPicByUserId(userId);
    }

    @Override
    public String getPicByUserOrderNo(String orderNo) {
        return userMapper.getPicByUserOrderNo(orderNo);
    }
}
