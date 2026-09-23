package com.insurance.copilot.documents;

import jakarta.validation.constraints.NotBlank;

public record IngestDocumentRequest(
        @NotBlank(message = "Document title is required") String title,
        @NotBlank(message = "Document category is required") String category,
        @NotBlank(message = "Document content is required") String content
) {
}
