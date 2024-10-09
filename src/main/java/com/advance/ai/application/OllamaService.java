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

    // TODO: llamaModle로 전환하기.
    private static final String CONTENT = "한글로 대답해";
    private static final String OLLAMA_API = "http://localhost:11434";
    private static final String OLLAMA_VER = "llama3";

    public ChatResponse sendSync(AiRequest req) {
        OllamaApi ollamaApi =
            new OllamaApi(OLLAMA_API);

        // Sync request
        var request = ChatRequest.builder(OLLAMA_VER)
            .withStream(false)
            .withMessages(List.of(
                Message.builder(Role.SYSTEM)
                    .withContent(CONTENT)
                    .build(),
                Message.builder(Role.USER)
                    .withContent(req.prom())
                    .build()))
            .withOptions(OllamaOptions.create().withTemperature(0.9))
            .build();

        return ollamaApi.chat(request);
    }

}
