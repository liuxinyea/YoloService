package com.example.demo.service.impl;

import com.example.demo.bean.User;
import com.example.demo.dao.UserMapper;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Override
    public User getUser(String username) {
        return userMapper.findByUsername(username);
    }
}
