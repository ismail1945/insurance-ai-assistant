package com.ismail.insuranceassistant.repository;

import com.ismail.insuranceassistant.domain.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {
}
