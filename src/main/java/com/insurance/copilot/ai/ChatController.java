package com.insurance.copilot.ai;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final AiOrchestrationService aiOrchestrationService;

    public ChatController(AiOrchestrationService aiOrchestrationService) {
        this.aiOrchestrationService = aiOrchestrationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ChatResponse ask(@Valid @RequestBody ChatRequest request, Authentication authentication) {
        return aiOrchestrationService.answer(authentication.getName(), request.question());
    }
}
