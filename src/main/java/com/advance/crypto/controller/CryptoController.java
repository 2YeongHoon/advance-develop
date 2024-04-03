package com.advance.crypto.controller;

import com.advance.crypto.controller.dto.CryptoRequest;
import com.advance.crypto.controller.dto.CryptoResponse;
import com.advance.crypto.service.CryptoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/crypto")
public class CryptoController {

    private final CryptoService cryptoService;

    @PostMapping
    public ResponseEntity<CryptoResponse> getDecrypt(@RequestBody CryptoRequest request) {
        return ResponseEntity.ok(cryptoService.getDecrypt(request));
    }

}
