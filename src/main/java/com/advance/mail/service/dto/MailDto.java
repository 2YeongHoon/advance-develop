package com.advance.mail.service.dto;

import com.advance.mail.enums.SendType;
import lombok.Getter;

@Getter
public class MailDto {

    private final byte[] file;
    private final String[] receivers;
    private final SendType sendType;

    private MailDto(String[] receivers, byte[] file, SendType sendType) {
        this.file = file;
        this.receivers = receivers;
        this.sendType = sendType;
    }

    public static MailDto makeTest(String[] receivers, byte[] file, SendType sendType) {
        return new MailDto(receivers, file, sendType);
    }

}
