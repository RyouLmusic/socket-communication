package com.hbr.weChat.model;

import lombok.Data;

@Data
public class Friends {
    private Integer id;
    private String ownAccount;
    private User user;
}
