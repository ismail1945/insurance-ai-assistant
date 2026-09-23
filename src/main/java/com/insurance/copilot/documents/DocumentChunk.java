package com.insurance.copilot.documents;

import jakarta.persistence.*;

@Entity
@Table(name = "document_chunks")
public class DocumentChunk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String documentTitle;
    private String category;

    @Column(length = 4000, nullable = false)
    private String content;

    @Column(length = 8000)
    private String embedding;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getDocumentTitle() { return documentTitle; }
    public void setDocumentTitle(String documentTitle) { this.documentTitle = documentTitle; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getEmbedding() { return embedding; }
    public void setEmbedding(String embedding) { this.embedding = embedding; }
}
