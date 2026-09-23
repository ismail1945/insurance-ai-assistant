package com.insurance.copilot.knowledge;

import com.insurance.copilot.documents.DocumentChunk;
import com.insurance.copilot.documents.DocumentService;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/knowledge")
@Validated
public class KnowledgeController {

    private final DocumentService documentService;
    private final int topK;

    public KnowledgeController(DocumentService documentService, @Value("${app.rag.top-k:4}") int topK) {
        this.documentService = documentService;
        this.topK = topK;
    }

    @GetMapping("/search")
    public List<DocumentChunk> search(@RequestParam @NotBlank(message = "Query is required") String query,
                                      @RequestParam(required = false) Integer limit) {
        int effectiveTopK = limit != null ? Math.max(1, limit) : topK;
        return documentService.semanticSearch(query, effectiveTopK);
    }
}
