package com.hbr.weChat.service;

import com.hbr.weChat.model.User;

public interface UserService {
    User select(String account, String password);

    void addUser(User user);

    void updateStatus(User user);
}
