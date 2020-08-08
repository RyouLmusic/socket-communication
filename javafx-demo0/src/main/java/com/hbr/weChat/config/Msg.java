package com.hbr.weChat.config;

public class Msg{
    /**
     * Integer 1 为自己， 0 为好友
     */

    Integer msgType;
    String msg;
    public Msg(Integer msgType, String msg){
        this.msgType = msgType;
        this.msg = msg;
    }
    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
