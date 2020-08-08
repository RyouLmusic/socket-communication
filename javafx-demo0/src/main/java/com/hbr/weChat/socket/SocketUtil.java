package com.hbr.weChat.socket;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

@Component
public class SocketUtil {

    public static boolean sendMessage(Socket socket, Object o) {
        try{
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(o);

        }catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static Object getMessage(Socket socket){
        Object o = null;
        try{
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            o = ois.readObject();
        }catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        return o;
    }
}
