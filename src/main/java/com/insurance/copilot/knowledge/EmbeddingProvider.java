package com.insurance.copilot.knowledge;

public interface EmbeddingProvider {
    double[] embed(String text);
}
