package com.hbr.weChat.dto;

import com.hbr.weChat.model.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResponseCode implements Serializable {
    public final static int SUCCESS = 2000;

    public final static int FALL = 4000;
    public final static int LOGIN_CODE = 8000; //登录
    public final static int REGISTER_CODE = 8080; //注册
    private Integer code;
    private String msg;
    private String detail;

    private User user;

    private List<User> users;
    private User ownInfo;
}
