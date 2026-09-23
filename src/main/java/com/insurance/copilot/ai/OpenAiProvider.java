package com.insurance.copilot.ai;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class OpenAiProvider implements AiProvider {

    private final RestTemplate restTemplate;
    private final String apiKey;
    private final String endpoint;
    private final String model;

    public OpenAiProvider(RestTemplate restTemplate,
                          @Value("${app.openai.api-key:}") String apiKey,
                          @Value("${app.openai.endpoint}") String endpoint,
                          @Value("${app.openai.model}") String model) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.endpoint = endpoint;
        this.model = model;
    }

    @Override
    @SuppressWarnings("unchecked")
    public String complete(String question, List<String> context) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(apiKey);

        String contextText = context.isEmpty() ? "No context." : String.join("\n", context);
        Map<String, Object> payload = Map.of(
                "model", model,
                "messages", List.of(
                        Map.of("role", "system", "content", "You are an insurance copilot. Give concise, accurate answers."),
                        Map.of("role", "user", "content", "Context:\n" + contextText + "\n\nQuestion: " + question)
                ),
                "temperature", 0.2
        );

        Map<String, Object> response = restTemplate.postForObject(endpoint, new HttpEntity<>(payload, headers), Map.class);
        if (response == null || !response.containsKey("choices")) {
            return "I could not generate a response at the moment.";
        }
        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.get("choices");
        if (choices.isEmpty()) {
            return "I could not generate a response at the moment.";
        }
        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
        return message.getOrDefault("content", "I could not generate a response at the moment.").toString();
    }
}
