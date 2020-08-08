package com.hbr.weChat.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {

    private String setMessageAccount;
    private String getMessageAccount;
    private String messageContent;
    private String head;
}
