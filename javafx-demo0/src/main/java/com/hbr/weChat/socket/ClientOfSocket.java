package com.hbr.weChat.socket;

import com.hbr.weChat.dto.ResponseCode;
import com.hbr.weChat.model.User;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Component
public class ClientOfSocket {


    public ResponseCode loginSocket(User user){
        ResponseCode code = new ResponseCode();
        ResponseCode codeFrom= null;
        try {
            Socket socket = new Socket("127.0.0.1", 16668);
            code.setUser(user);
            code.setCode(ResponseCode.LOGIN_CODE);
            ClientSocketThread socketThread = new ClientSocketThread(socket, code);
            socketThread.start();

            ManageClientSocket.addClientSocketThread(user.getAccount(), socketThread);

            codeFrom = (ResponseCode) SocketUtil.getMessage(socket);
            if (codeFrom.getCode() == ResponseCode.SUCCESS){
                MessageThread messageThread = new MessageThread(socket);
                messageThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return codeFrom;
    }
}
