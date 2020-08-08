package com.hbr.weChat.service;

import com.hbr.weChat.dto.ResponseCode;
import com.hbr.weChat.model.User;
import com.hbr.weChat.socket.ClientOfSocket;
import com.hbr.weChat.socket.ManageClientSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    ClientOfSocket clientOfSocket;

    public ResponseCode login(String account, String password){


        User user = new User();
        user.setAccount(account);
        user.setPassword(password);

        ResponseCode code = clientOfSocket.loginSocket(user);

        return code;
    }

}
