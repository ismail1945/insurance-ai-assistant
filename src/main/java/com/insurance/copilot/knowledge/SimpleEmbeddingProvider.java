package com.insurance.copilot.knowledge;

import org.springframework.stereotype.Component;

@Component
public class SimpleEmbeddingProvider implements EmbeddingProvider {

    private static final int SIZE = 24;

    @Override
    public double[] embed(String text) {
        double[] vector = new double[SIZE];
        for (int i = 0; i < text.length(); i++) {
            int index = i % SIZE;
            vector[index] += Character.toLowerCase(text.charAt(i));
        }
        normalize(vector);
        return vector;
    }

    private static void normalize(double[] vector) {
        double sum = 0.0;
        for (double v : vector) {
            sum += v * v;
        }
        if (sum == 0) {
            return;
        }
        double norm = Math.sqrt(sum);
        for (int i = 0; i < vector.length; i++) {
            vector[i] /= norm;
        }
    }
}
