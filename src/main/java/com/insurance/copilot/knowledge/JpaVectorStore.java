package com.insurance.copilot.knowledge;

import com.insurance.copilot.documents.DocumentChunk;
import com.insurance.copilot.documents.DocumentChunkRepository;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Component
public class JpaVectorStore implements VectorStore {

    private final DocumentChunkRepository repository;
    private final EmbeddingProvider embeddingProvider;

    public JpaVectorStore(DocumentChunkRepository repository, EmbeddingProvider embeddingProvider) {
        this.repository = repository;
        this.embeddingProvider = embeddingProvider;
    }

    @Override
    public void save(DocumentChunk chunk, double[] embedding) {
        chunk.setEmbedding(toString(embedding));
        repository.save(chunk);
    }

    @Override
    public List<DocumentChunk> search(String query, int topK) {
        double[] queryVector = embeddingProvider.embed(query);
        return repository.findAll().stream()
                .filter(chunk -> chunk.getEmbedding() != null)
                .sorted(Comparator.comparingDouble(chunk -> -cosineSimilarity(queryVector, fromString(chunk.getEmbedding()))))
                .limit(topK)
                .toList();
    }

    private static double cosineSimilarity(double[] a, double[] b) {
        int size = Math.min(a.length, b.length);
        double dot = 0;
        for (int i = 0; i < size; i++) {
            dot += a[i] * b[i];
        }
        return dot;
    }

    private static String toString(double[] values) {
        return Arrays.stream(values).mapToObj(Double::toString).reduce((a, b) -> a + "," + b).orElse("");
    }

    private static double[] fromString(String value) {
        return Arrays.stream(value.split(",")).mapToDouble(Double::parseDouble).toArray();
    }
}
