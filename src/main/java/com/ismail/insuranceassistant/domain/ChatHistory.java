package com.ismail.insuranceassistant.domain;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "chat_history", indexes = {
        @Index(name = "idx_chat_customer", columnList = "customerId"),
        @Index(name = "idx_chat_created", columnList = "createdAt")
})
public class ChatHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String question;

    @Column(nullable = false)
    private String detectedIntent;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String answer;

    @Column(nullable = false)
    private boolean fallbackMode;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String citations;

    @Column(nullable = false, updatable = false)
    private Instant createdAt = Instant.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getQuestion() { return question; }
    public void setQuestion(String question) { this.question = question; }
    public String getDetectedIntent() { return detectedIntent; }
    public void setDetectedIntent(String detectedIntent) { this.detectedIntent = detectedIntent; }
    public String getAnswer() { return answer; }
    public void setAnswer(String answer) { this.answer = answer; }
    public boolean isFallbackMode() { return fallbackMode; }
    public void setFallbackMode(boolean fallbackMode) { this.fallbackMode = fallbackMode; }
    public String getCitations() { return citations; }
    public void setCitations(String citations) { this.citations = citations; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
