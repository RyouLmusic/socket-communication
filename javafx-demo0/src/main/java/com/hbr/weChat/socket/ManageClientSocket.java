package com.hbr.weChat.socket;

import java.util.HashMap;

public class ManageClientSocket {
    public static HashMap<String, ClientSocketThread> map = new HashMap<>();

    public static void addClientSocketThread(String account, ClientSocketThread socketThread){
        map.put(account, socketThread);
    }
    public static ClientSocketThread getClientSocketThread(String account){
        return map.get(account);
    }
}
