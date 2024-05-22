package com.advance.mail.controller;


import com.advance.mail.service.MailService;
import com.advance.mail.service.dto.MailDto;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RestController
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;

    @GetMapping
    public ResponseEntity<Void> sendMail(MultipartFile file) throws IOException {
        // Controller를 통해 multipartfile 입력 시 웹서버 내부에 임시 저장.
        // 하지만, 해당 공간은 thread 별로 할당되므로 비동기 함수로 처리 시 다른 thread가 처리하면
        // 참조하지 못하게 되어 NoSuchFileException이 발생.
        // 따라서 InputStreamSource 객체로 변환
        InputStreamSource source = new ByteArrayResource(file.getBytes());
        String[] receiver = {"djaakclsrn12@naver.com"};
        mailService.send(MailDto.makeTest(receiver, source));
        return ResponseEntity.ok().build();
    }

}
