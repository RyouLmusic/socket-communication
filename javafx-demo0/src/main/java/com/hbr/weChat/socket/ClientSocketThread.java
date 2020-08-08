package com.hbr.weChat.socket;

import com.hbr.weChat.dto.ResponseCode;
import com.hbr.weChat.model.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketThread extends Thread {
    Socket socket;
    ResponseCode code;
    public ClientSocketThread(Socket socket, ResponseCode code){
        this.socket = socket;
        this.code = code;
    }
    @Override
    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(code);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket(){
        return socket;
    }
}
