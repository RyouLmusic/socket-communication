package com.hbr.weChat.service;

import com.hbr.weChat.dto.ResponseCode;
import com.hbr.weChat.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsService {

    @Autowired
    LoginService loginService;

    public List<User> selectFriends(String account, String password){
        ResponseCode code = loginService.login(account, password);
        List<User> users = code.getUsers();
        return users;
    }
}
