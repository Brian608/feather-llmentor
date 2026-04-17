package org.feather.llm.langchain4j.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import org.feather.llm.langchain4j.domain.Book;
import reactor.core.publisher.Flux;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.langchain4j.service
 * @className: LangChainAiService
 * @author: feather
 * @description:
 * @since: 2026-04-17 2:37 PM
 * @version: 1.0
 */
@AiService
public interface LangChainAiService {

    String chat(String message);

    Flux<String> chatStream(String message);

    @SystemMessage("你是一个毒舌博主，擅长怼人")
    @UserMessage("针对用户的内容：{{topic}}，先复述一遍他的问题，然后再回答")
    Flux<String> chatTemplate(String topic);

    @SystemMessage("你是一个专业的图书推荐人员")
    @UserMessage("{{message}}")
    Book getBooks(String message);

}
