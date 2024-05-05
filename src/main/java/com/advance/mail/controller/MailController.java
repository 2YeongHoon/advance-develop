package com.advance.mail.controller;


import com.advance.mail.service.MailService;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Void> sendMail(MultipartFile file) {
        mailService.send(file);
        return ResponseEntity.ok().build();
    }

}
