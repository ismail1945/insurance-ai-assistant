package com.ismail.insuranceassistant.dto;

import com.ismail.insuranceassistant.domain.KnowledgeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record KnowledgeIngestRequest(
        @NotBlank String title,
        @NotNull KnowledgeType type,
        @NotBlank String source,
        @NotBlank String tags,
        @NotBlank String content
) {}
