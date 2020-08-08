package com.hbr.weChat.model;

import lombok.Data;

@Data
public class FileMessage extends Message {

    private byte[] fileContent;
}
