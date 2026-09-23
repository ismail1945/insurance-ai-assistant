package com.insurance.copilot.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AiProviderSelector {

    private final OpenAiProvider openAiProvider;
    private final MockAiProvider mockAiProvider;
    private final boolean openAiEnabled;
    private final boolean fallbackEnabled;
    private final String apiKey;

    public AiProviderSelector(OpenAiProvider openAiProvider,
                              MockAiProvider mockAiProvider,
                              @Value("${app.openai.enabled:true}") boolean openAiEnabled,
                              @Value("${app.ai.fallback-enabled:true}") boolean fallbackEnabled,
                              @Value("${app.openai.api-key:}") String apiKey) {
        this.openAiProvider = openAiProvider;
        this.mockAiProvider = mockAiProvider;
        this.openAiEnabled = openAiEnabled;
        this.fallbackEnabled = fallbackEnabled;
        this.apiKey = apiKey;
    }

    public AiProvider activeProvider() {
        if (openAiEnabled && apiKey != null && !apiKey.isBlank()) {
            return openAiProvider;
        }
        if (!fallbackEnabled) {
            throw new IllegalStateException("OPENAI_API_KEY is missing and fallback mode is disabled");
        }
        return mockAiProvider;
    }
}
