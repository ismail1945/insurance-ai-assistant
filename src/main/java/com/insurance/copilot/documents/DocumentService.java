package com.insurance.copilot.documents;

import com.insurance.copilot.knowledge.EmbeddingProvider;
import com.insurance.copilot.knowledge.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentService {

    private final DocumentChunker chunker;
    private final EmbeddingProvider embeddingProvider;
    private final VectorStore vectorStore;

    public DocumentService(DocumentChunker chunker, EmbeddingProvider embeddingProvider, VectorStore vectorStore) {
        this.chunker = chunker;
        this.embeddingProvider = embeddingProvider;
        this.vectorStore = vectorStore;
    }

    public int ingest(IngestDocumentRequest request) {
        List<String> chunks = chunker.chunk(request.content());
        for (String chunkContent : chunks) {
            DocumentChunk chunk = new DocumentChunk();
            chunk.setDocumentTitle(request.title());
            chunk.setCategory(request.category());
            chunk.setContent(chunkContent);
            vectorStore.save(chunk, embeddingProvider.embed(chunkContent));
        }
        return chunks.size();
    }

    public List<DocumentChunk> semanticSearch(String query, int topK) {
        return vectorStore.search(query, topK);
    }
}
