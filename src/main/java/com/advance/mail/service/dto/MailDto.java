package com.advance.mail.service.dto;

import lombok.Getter;
import org.springframework.core.io.InputStreamSource;

@Getter
public class MailDto {

    private final InputStreamSource file;
    private final String[] receivers;

    private MailDto(String[] receivers, InputStreamSource file) {
        this.file = file;
        this.receivers = receivers;
    }

    public static MailDto makeTest(String[] receivers, InputStreamSource file) {
        return new MailDto(receivers, file);
    }

}
