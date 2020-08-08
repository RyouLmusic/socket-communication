package com.hbr.weChat.config;

import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class ManageMessage {

    public static ListView<Pane> chatList;
    public static final HashMap<String, List<Msg>> msgMap = new HashMap<>();

    public static void addMsg(String account, Msg msg){
        List<Msg> msgList = getMsg(account);
        msgList.add(msg);
        msgMap.put(account, msgList);
    }
    public static List<Msg> getMsg(String account){
        if (msgMap.get(account) == null){
            List<Msg> msgList = new ArrayList<>();
            msgMap.put(account, msgList);
        }
        return msgMap.get(account);
    }
    public static ListView getChatList(){
        return chatList;
    }
}

