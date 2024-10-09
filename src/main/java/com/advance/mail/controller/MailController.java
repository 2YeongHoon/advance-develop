package com.advance.mail.controller;


import com.advance.mail.service.MailService;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
public class MailController {

    private final MailService mailService;

    @GetMapping(value = "/async")
    public ResponseEntity<Void> sendAsync(MultipartFile file) throws IOException {
        mailService.sendAsync(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/sync")
    public ResponseEntity<Void> sendSync(MultipartFile file) throws IOException {
        mailService.sendSync(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/mq")
    public ResponseEntity<Void> sendSyncAndMq(MultipartFile file) throws IOException {
        mailService.sendSyncAndMq(file);
        return ResponseEntity.ok().build();
    }

}
