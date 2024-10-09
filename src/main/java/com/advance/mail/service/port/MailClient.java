package com.advance.mail.service.port;

import com.advance.mail.service.dto.MailDto;

public interface MailClient {

    void send(MailDto dto);

}
