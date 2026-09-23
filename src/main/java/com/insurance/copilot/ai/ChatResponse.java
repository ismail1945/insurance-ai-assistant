package com.insurance.copilot.ai;

import java.util.List;

public record ChatResponse(String answer, List<String> context) {
}
