package com.ismail.insuranceassistant.dto;

import jakarta.validation.constraints.NotBlank;

public record ChatAskRequest(
        @NotBlank String customerId,
        @NotBlank String question
) {}
