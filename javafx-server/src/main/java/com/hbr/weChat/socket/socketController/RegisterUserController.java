package com.hbr.weChat.socket.socketController;

import com.hbr.weChat.dto.ResponseCode;
import com.hbr.weChat.model.User;
import com.hbr.weChat.service.UserService;
import com.hbr.weChat.socket.ManageServerSocket;
import com.hbr.weChat.socket.SocketUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class RegisterUserController {


    @Autowired
    UserService userService;

    public void sendToClient(User user) {

        ResponseCode backCode = new ResponseCode();

        if(user != null){
            backCode.setCode(ResponseCode.SUCCESS);
        }else {
            backCode.setCode(ResponseCode.FALL);
        }
        SocketUtil.send(ManageServerSocket.getRegisterSocket().getSocket(), backCode);
    }
}
