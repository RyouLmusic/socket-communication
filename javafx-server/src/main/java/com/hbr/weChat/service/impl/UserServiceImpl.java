package com.hbr.weChat.service.impl;

import com.hbr.weChat.mapper.UserMapper;
import com.hbr.weChat.model.User;
import com.hbr.weChat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper mapper;

    @Override
    public User select(String account, String password) {
        return mapper.select(account, password);
    }

    @Override
    public void addUser(User user) {
        //TODO 保存User
        mapper.addUser(user);
    }

    @Override
    public void updateStatus(User user) {
        //更新账号状态  -- 在线Status=1
        mapper.updateStatus(user);
    }
}
