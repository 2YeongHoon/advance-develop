package com.advance.ai.controller.dto;

public record AiResponse(org.springframework.ai.ollama.api.OllamaApi.Message answer) {

}
