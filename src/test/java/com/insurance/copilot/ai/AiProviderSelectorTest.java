package com.insurance.copilot.ai;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AiProviderSelectorTest {

    private final OpenAiProvider openAiProvider = new OpenAiProvider(new org.springframework.web.client.RestTemplate(), "key", "https://example.com", "gpt-4o-mini");
    private final MockAiProvider mockAiProvider = new MockAiProvider();

    @Test
    void shouldUseOpenAiWhenApiKeyIsPresent() {
        AiProviderSelector selector = new AiProviderSelector(openAiProvider, mockAiProvider, true, true, "abc");
        assertInstanceOf(OpenAiProvider.class, selector.activeProvider());
    }

    @Test
    void shouldUseMockWhenApiKeyMissing() {
        AiProviderSelector selector = new AiProviderSelector(openAiProvider, mockAiProvider, true, true, "");
        assertInstanceOf(MockAiProvider.class, selector.activeProvider());
    }

    @Test
    void shouldThrowWhenNoKeyAndFallbackDisabled() {
        AiProviderSelector selector = new AiProviderSelector(openAiProvider, mockAiProvider, true, false, "");
        assertThrows(IllegalStateException.class, selector::activeProvider);
    }
}
