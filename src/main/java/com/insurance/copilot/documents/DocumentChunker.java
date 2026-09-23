package com.insurance.copilot.documents;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DocumentChunker {

    private static final int CHUNK_SIZE = 400;

    public List<String> chunk(String content) {
        List<String> chunks = new ArrayList<>();
        String normalized = content.replaceAll("\\s+", " ").trim();
        for (int i = 0; i < normalized.length(); i += CHUNK_SIZE) {
            chunks.add(normalized.substring(i, Math.min(normalized.length(), i + CHUNK_SIZE)));
        }
        return chunks;
    }
}
