package com.hbr.weChat.socket;

import com.hbr.weChat.model.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MessageThread extends Thread {

    Socket socket;
    public MessageThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        while (true){
            try{
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();

                //TODO 发送给
                Socket s = ManageServerSocket.getServerSocketThread(message.getGetMessageAccount()).getSocket();
                SocketUtil.send(s, message);
                System.out.println(message.getMessageContent());
            }catch (IOException | ClassNotFoundException e) {

                e.printStackTrace();
                System.out.println("客户端关闭了");
                break;
            }
        }
    }
}
