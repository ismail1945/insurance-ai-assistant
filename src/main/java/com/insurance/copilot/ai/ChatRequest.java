package com.insurance.copilot.ai;

import jakarta.validation.constraints.NotBlank;

public record ChatRequest(
        @NotBlank(message = "Question is required") String question
) {
}
