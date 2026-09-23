package com.insurance.copilot.knowledge;

import com.insurance.copilot.documents.DocumentChunk;

import java.util.List;

public interface VectorStore {
    void save(DocumentChunk chunk, double[] embedding);
    List<DocumentChunk> search(String query, int topK);
}
