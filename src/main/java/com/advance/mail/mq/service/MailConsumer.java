package com.advance.mail.mq.service;

import com.advance.mail.infrastructure.MqMailService;
import com.advance.mail.service.dto.MqMailDto;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailConsumer {

    private final MqMailService mailService;

    @RabbitListener(queues = "mail_queue")
    public void receiveEmailRequest(MqMailDto dto) throws MessagingException {
        mailService.sendMessage(dto);
    }

}
