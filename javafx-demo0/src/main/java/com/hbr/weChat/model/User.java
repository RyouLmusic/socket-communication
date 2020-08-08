package com.hbr.weChat.model;

import lombok.Data;


import java.io.Serializable;

@Data
public class User implements Serializable {
    //    id, account, password, name, age, sex, head, address, label, phone, background, status
    private Integer id;
    private String account;
    private String password;
    private String name;
    private int age;
    private String sex;
    private String head;
    private String address;
    private String label;//个性标签
    private String phone;
    private String background;
    private Integer status; //1為在线，0為注销
}
