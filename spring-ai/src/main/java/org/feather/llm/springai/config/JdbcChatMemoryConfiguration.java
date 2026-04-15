package org.feather.llm.springai.config;

import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.chat.memory.repository.jdbc.JdbcChatMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.springai.config
 * @className: JdbcChatMemoryConfiguration
 * @author: feather
 * @description:
 * @since: 2026-04-15 9:42 PM
 * @version: 1.0
 */
@Configuration
public class JdbcChatMemoryConfiguration {
    @Bean
    public ChatMemory jdbcChatMemory(JdbcChatMemoryRepository jdbcChatMemoryRepository) {
        return MessageWindowChatMemory.builder().chatMemoryRepository(jdbcChatMemoryRepository).maxMessages(20).build();
    }
}
