package com.advance.ai.application;

import com.advance.ai.controller.dto.AiRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.ollama.OllamaChatClient;
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

  private final OllamaChatClient chatClient;

  public ChatResponse test(AiRequest req) {
    OllamaApi ollamaApi =
        new OllamaApi("http://localhost:11434");

    // Sync request
    var request = ChatRequest.builder("llama3")
        .withStream(false) // not streaming
        .withMessages(List.of(
            Message.builder(Role.SYSTEM)
                .withContent("You are geography teacher. You are talking to a student.")
                .build(),
            Message.builder(Role.USER)
                .withContent(req.prom())
                .build()))
        .withOptions(OllamaOptions.create().withTemperature(0.9f))
        .build();

    return ollamaApi.chat(request);
  }
}
