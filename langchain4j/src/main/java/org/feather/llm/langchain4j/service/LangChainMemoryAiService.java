package org.feather.llm.langchain4j.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.langchain4j.service
 * @className: LangChainMemoryAiService
 * @author: feather
 * @description:
 * @since: 2026-04-17 3:01 PM
 * @version: 1.0
 */
@AiService
public interface LangChainMemoryAiService {

    String chatMemory(@MemoryId String memoryId, @UserMessage String userMessage);
}
