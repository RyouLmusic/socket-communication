package com.hbr.weChat.service;

import com.hbr.weChat.dto.ResponseCode;
import com.hbr.weChat.model.User;
import com.hbr.weChat.socket.RegisterSocketThread;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Service
public class RegisterService {

    public ResponseCode register(User user) {
        ResponseCode code = new ResponseCode();
        ResponseCode codeFrom = null;

        try {
            Socket socket = new Socket("127.0.0.1", 16668);
            code.setUser(user);
            code.setCode(ResponseCode.REGISTER_CODE);
            RegisterSocketThread socketThread = new RegisterSocketThread(socket, code);
            FutureTask<ResponseCode> ft = new FutureTask<>(socketThread);
            new Thread(ft).start();
            codeFrom = ft.get();
        } catch (IOException | InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        return codeFrom;
    }
}