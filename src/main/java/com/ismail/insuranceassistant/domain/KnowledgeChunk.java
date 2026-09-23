package com.ismail.insuranceassistant.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "knowledge_chunks", indexes = {
        @Index(name = "idx_chunk_keywords", columnList = "keywords")
})
public class KnowledgeChunk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", nullable = false)
    private KnowledgeDocument document;

    @Column(nullable = false)
    private Integer chunkIndex;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private String keywords;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public KnowledgeDocument getDocument() { return document; }
    public void setDocument(KnowledgeDocument document) { this.document = document; }
    public Integer getChunkIndex() { return chunkIndex; }
    public void setChunkIndex(Integer chunkIndex) { this.chunkIndex = chunkIndex; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getKeywords() { return keywords; }
    public void setKeywords(String keywords) { this.keywords = keywords; }
}
