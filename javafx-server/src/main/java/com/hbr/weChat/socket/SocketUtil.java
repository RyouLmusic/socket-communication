package com.hbr.weChat.socket;


import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketUtil {


    public static void send(Socket socket, Object o){
        try{
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(o);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
