package com.insurance.copilot.ai;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MockAiProvider implements AiProvider {
    @Override
    public String complete(String question, List<String> context) {
        String contextualPrefix = context.isEmpty() ? "I could not find matching policy documentation." : "Based on your policy knowledge base";
        return contextualPrefix + ", here is guidance: " + question;
    }
}
