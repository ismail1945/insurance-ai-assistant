package com.insurance.copilot.ai;

import com.insurance.copilot.documents.DocumentChunk;
import com.insurance.copilot.documents.DocumentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AiOrchestrationService {

    private static final Logger log = LoggerFactory.getLogger(AiOrchestrationService.class);

    private final DocumentService documentService;
    private final ChatMessageRepository chatMessageRepository;
    private final AiProviderSelector aiProviderSelector;
    private final int topK;

    public AiOrchestrationService(DocumentService documentService,
                                  ChatMessageRepository chatMessageRepository,
                                  AiProviderSelector aiProviderSelector,
                                  @Value("${app.rag.top-k:4}") int topK) {
        this.documentService = documentService;
        this.chatMessageRepository = chatMessageRepository;
        this.aiProviderSelector = aiProviderSelector;
        this.topK = topK;
    }

    public ChatResponse answer(String username, String question) {
        List<DocumentChunk> chunks = documentService.semanticSearch(question, topK);
        List<String> context = chunks.stream().map(DocumentChunk::getContent).toList();

        String answer = aiProviderSelector.activeProvider().complete(question, context);
        log.info("chat_answer_generated username={} context_count={}", username, context.size());

        ChatMessage message = new ChatMessage();
        message.setUsername(username);
        message.setQuestion(question);
        message.setAnswer(answer);
        message.setContextRefs(chunks.stream().map(c -> c.getDocumentTitle() + "#" + c.getId()).reduce((a, b) -> a + "," + b).orElse(""));
        chatMessageRepository.save(message);

        return new ChatResponse(answer, chunks.stream().map(DocumentChunk::getDocumentTitle).distinct().toList());
    }
}
