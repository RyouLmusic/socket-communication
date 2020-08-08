package com.hbr.weChat.socket;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 保存线程信息，每个上线都保存进来
 */
public class ManageServerSocket {

    /**
     * 需要多次取出
     */
    public static HashMap<String , ServerSocketThread> map = new HashMap<>();

    public static void addServerSocketThread(String loginAccount, ServerSocketThread socketThread){
        map.put(loginAccount, socketThread);
    }
    public static ServerSocketThread getServerSocketThread(String loginAccount){
       return map.get(loginAccount);
    }

    /**
     * 注册用的，每次取出都会直接删除里面的元素
     */
    public static ArrayList<ServerSocketThread> arrayList = new ArrayList<>();
    public static void addRegisterSocket(ServerSocketThread socketThread){
        arrayList.add(socketThread);
    }
    public static ServerSocketThread getRegisterSocket(){
        return arrayList.remove(0);
    }
}
