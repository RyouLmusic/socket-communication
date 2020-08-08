package com.hbr.weChat.socket;

import com.hbr.weChat.config.ManageMessage;
import com.hbr.weChat.config.Msg;
import com.hbr.weChat.config.Tool;
import com.hbr.weChat.controller.MainWindowController;
import com.hbr.weChat.model.Message;
import com.hbr.weChat.view.ChatListItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

public class MessageThread extends Thread{

    Socket socket;

    public MessageThread(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run(){
        while (true){
            try {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                Message message = (Message) ois.readObject();
                //TODO 得到后直接存到message
                Msg msg = new Msg(0, message.getMessageContent());
                if (ManageMessage.getChatList() != null){
                    ManageMessage.getChatList().getItems().add(new ChatListItem().Left(message.getHead(),msg.getMsg(), Tool.getWidth(msg.getMsg()),Tool.getHeight(msg.getMsg())));
                }
                ManageMessage.addMsg(message.getSetMessageAccount(), msg);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
