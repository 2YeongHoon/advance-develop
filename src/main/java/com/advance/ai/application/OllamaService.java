package com.advance.ai.application;

import com.advance.ai.controller.dto.AiRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaApi.ChatRequest;
import org.springframework.ai.ollama.api.OllamaApi.ChatResponse;
import org.springframework.ai.ollama.api.OllamaApi.Message;
import org.springframework.ai.ollama.api.OllamaApi.Message.Role;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OllamaService {

    private static final String CONTENT = "한글로 대답해";
    private static final String OLLAMA_API = "http://localhost:11434";
    private static final String OLLAMA_VER = "llama3";

    public ChatResponse sendSync(AiRequest req) {
        OllamaApi ollamaApi =
            new OllamaApi(OLLAMA_API);

        var request = ChatRequest.builder(OLLAMA_VER)
            .stream(false)
            .messages(List.of(
                Message.builder(Role.SYSTEM)
                    .content(CONTENT)
                    .build(),
                Message.builder(Role.USER)
                    .content(req.prom())
                    .build()))
            .options(OllamaOptions.builder().temperature(0.9).build())
            .build();

        return ollamaApi.chat(request);
    }

}