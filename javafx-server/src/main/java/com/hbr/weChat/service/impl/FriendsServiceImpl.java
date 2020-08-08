package com.hbr.weChat.service.impl;

import com.hbr.weChat.mapper.FriendsMapper;
import com.hbr.weChat.model.Friends;
import com.hbr.weChat.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    FriendsMapper mapper;
    @Override
    public List<Friends> Select(String account) {
        return mapper.select(account);
    }
}
