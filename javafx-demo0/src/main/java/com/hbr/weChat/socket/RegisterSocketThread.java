package com.hbr.weChat.socket;

import com.hbr.weChat.dto.ResponseCode;

import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

public class RegisterSocketThread implements Callable<ResponseCode>{
    Socket socket;
    ResponseCode code;
    public RegisterSocketThread(Socket socket, ResponseCode code){
        this.socket = socket;
        this.code = code;
    }
    @Override
    public ResponseCode call(){
        ResponseCode codeFrom = new ResponseCode();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

            oos.writeObject(code);
            System.out.println(code);
            codeFrom = (ResponseCode) SocketUtil.getMessage(socket);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return codeFrom;
    }

    public Socket getSocket() {
        return socket;
    }
}
