package com.advance.ai.controller;

import com.advance.ai.application.OllamaService;
import com.advance.ai.controller.dto.AiRequest;
import com.advance.ai.controller.dto.AiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.ollama.api.OllamaApi.ChatResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
public class AiController {

    private final OllamaService ollamaService;

    @PostMapping
    public ResponseEntity<AiResponse> getDecrypt(@RequestBody AiRequest request) {
        ChatResponse chatRes = ollamaService.sendSync(request);
        return ResponseEntity.ok(new AiResponse(chatRes.message().content()));
    }

}
