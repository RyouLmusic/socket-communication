package com.hbr.weChat.socket;

import com.hbr.weChat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegisterSocket {
    @Autowired
    UserService userService;



}
