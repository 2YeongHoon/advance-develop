package com.advance.mail.service.dto;

import com.advance.mail.enums.SendType;
import java.io.Serializable;

public class MqMailDto implements Serializable {

    private final byte[] file;
    private final String[] receivers;
    private final SendType sendType;

    private MqMailDto(String[] receivers, byte[] file, SendType sendType) {
        this.file = file;
        this.receivers = receivers;
        this.sendType = sendType;
    }

    public byte[] getFile() {
        return file;
    }

    public String[] getReceivers() {
        return receivers;
    }

    public SendType getSendType() {
        return sendType;
    }

    public static MqMailDto makeTest(String[] receivers, byte[] file, SendType sendType) {
        return new MqMailDto(receivers, file, sendType);
    }

}
