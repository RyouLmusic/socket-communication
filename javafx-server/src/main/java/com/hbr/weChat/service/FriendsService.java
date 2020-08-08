package com.hbr.weChat.service;

import com.hbr.weChat.model.Friends;

import java.util.List;

public interface FriendsService {
    List<Friends> Select(String account);
}
