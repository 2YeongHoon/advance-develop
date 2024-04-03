package com.advance.crypto.service;

import com.advance.crypto.controller.dto.CryptoRequest;
import com.advance.crypto.controller.dto.CryptoResponse;
import org.springframework.stereotype.Service;

@Service
public class CryptoService {

  public CryptoResponse getDecrypt(CryptoRequest request) {
    return new CryptoResponse(request.getName());
  }

}
