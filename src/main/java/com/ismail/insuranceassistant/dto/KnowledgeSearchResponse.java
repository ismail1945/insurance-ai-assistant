package com.ismail.insuranceassistant.dto;

public record KnowledgeSearchResponse(
        Long documentId,
        String title,
        String source,
        String snippet
) {}
