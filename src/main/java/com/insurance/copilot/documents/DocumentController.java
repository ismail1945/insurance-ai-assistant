package com.insurance.copilot.documents;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @PostMapping("/ingest")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> ingest(@Valid @RequestBody IngestDocumentRequest request) {
        int chunks = documentService.ingest(request);
        return Map.of("document", request.title(), "chunksCreated", chunks);
    }
}
