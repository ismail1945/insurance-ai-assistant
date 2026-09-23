package com.ismail.insuranceassistant.repository;

import com.ismail.insuranceassistant.domain.KnowledgeDocument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnowledgeDocumentRepository extends JpaRepository<KnowledgeDocument, Long> {
}
