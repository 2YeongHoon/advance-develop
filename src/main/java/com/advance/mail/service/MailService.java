package com.advance.mail.service;

import com.advance.mail.enums.SendType;
import com.advance.mail.service.dto.MailDto;
import com.advance.mail.service.dto.MqMailDto;
import com.advance.mail.service.port.MailClient;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MailService {

    public static final String QUEUE_NAME = "mail_queue";

    private final MailClient mailClient;
    private final RabbitTemplate rabbitTemplate;

    public void sendSync(MultipartFile file) throws IOException {
        String[] receiver = {"djaakclsrn12@naver.com"};
        mailClient.send(MailDto.makeTest(receiver, file.getBytes(), SendType.SYNC));
    }

    public void sendAsync(MultipartFile file) throws IOException {
        String[] receiver = {"djaakclsrn12@naver.com"};
        mailClient.send(MailDto.makeTest(receiver, file.getBytes(), SendType.ASYNC));
    }

    public void sendSyncAndMq(MultipartFile file) throws IOException {
        String[] receiver = {"djaakclsrn12@naver.com"};
        rabbitTemplate.convertAndSend(QUEUE_NAME, MqMailDto.makeTest(receiver, file.getBytes(), SendType.SYNC));
    }

}
