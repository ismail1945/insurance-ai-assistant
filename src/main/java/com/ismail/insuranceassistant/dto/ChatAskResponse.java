package com.ismail.insuranceassistant.dto;

import java.util.List;

public record ChatAskResponse(
        String intent,
        String answer,
        List<String> citations,
        boolean fallbackMode
) {}
