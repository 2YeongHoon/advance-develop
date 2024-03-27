package com.advance.crypto.controller;

import com.advance.crypto.controller.dto.CryptoRequest;
import com.advance.crypto.controller.dto.CryptoResponse;
import com.advance.crypto.controller.dto.DecryptRequestDto;
import com.advance.crypto.utils.Aes256Utils;
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

    @PostMapping
    public ResponseEntity<CryptoResponse> getDecrypt(@RequestBody CryptoRequest request) {
        CryptoResponse response = new CryptoResponse(request.getName());

        return ResponseEntity.ok(response);
    }

}
