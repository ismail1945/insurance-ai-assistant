package com.ismail.insuranceassistant.repository;

import com.ismail.insuranceassistant.domain.KnowledgeChunk;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KnowledgeChunkRepository extends JpaRepository<KnowledgeChunk, Long> {

    @Query("""
        SELECT kc FROM KnowledgeChunk kc
        WHERE lower(kc.content) LIKE lower(concat('%', :term, '%'))
           OR lower(kc.keywords) LIKE lower(concat('%', :term, '%'))
        ORDER BY kc.chunkIndex ASC
        """)
    List<KnowledgeChunk> keywordSearch(@Param("term") String term);
}
