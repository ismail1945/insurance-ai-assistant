package com.insurance.copilot.ai;

import java.util.List;

public interface AiProvider {
    String complete(String question, List<String> context);
}
