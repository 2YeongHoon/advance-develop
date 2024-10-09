package com.advance.mail.infrastructure;

import com.advance.mail.enums.SendType;
import com.advance.mail.service.dto.MailDto;
import com.advance.mail.service.port.MailClient;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailClientImpl implements MailClient {

    private final SyncTestMailService syncTestMailService;
    private final AsyncTestMailService asyncTestMailService;

    @Override
    public void send(MailDto dto) {
        try {
            messageBuildAndSend(dto);
        } catch (MessagingException e) {
            log.error(e.toString());
        }
    }

    private void messageBuildAndSend(MailDto dto) throws MessagingException {
        if (SendType.SYNC.equals(dto.getSendType())) {
            syncTestMailService.makeMassage(dto);
        }

        if (SendType.ASYNC.equals(dto.getSendType())) {
            asyncTestMailService.makeMassage(dto);
        }

    }
}
