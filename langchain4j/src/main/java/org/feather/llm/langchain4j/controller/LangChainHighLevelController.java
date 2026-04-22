package org.feather.llm.langchain4j.controller;

import com.alibaba.fastjson2.JSON;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import jakarta.servlet.http.HttpServletResponse;
import org.feather.llm.langchain4j.chatMemory.RedisChatMemoryStore;
import org.feather.llm.langchain4j.domain.Book;
import org.feather.llm.langchain4j.service.LangChainAiService;
import org.feather.llm.langchain4j.service.LangChainMemoryAiService;
import org.feather.llm.langchain4j.tools.TemperatureTools;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.langchain4j.controller
 * @className: LangChainHighLevelController
 * @author: feather
 * @description:
 * @since: 2026-04-17 2:35 PM
 * @version: 1.0
 */
@RequestMapping("/langchain/high")
@RestController
public class LangChainHighLevelController  implements InitializingBean {

    @Autowired
    private LangChainAiService aiService;

    private LangChainMemoryAiService memoryAiService;


    @Autowired
    private RedisChatMemoryStore redisChatMemoryStore;

    @Autowired
    OpenAiChatModel chatModel;

    @GetMapping("/chat")
    public String chat(String message,HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        return aiService.chat(message);
    }


    @GetMapping("/chatStream")
    public Flux<String> chatStream(String message, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        return aiService.chatStream(message);
    }

    @GetMapping("/chatTemplate")
    public Flux<String> chatTemplate(String message,HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        return aiService.chatTemplate(message);
    }

    @GetMapping("/structure")
    public String structure(String message,HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        Book books = aiService.getBooks(message);
        return JSON.toJSONString(books);
    }

    @GetMapping("/memoryChat")
    public String memoryChat(HttpServletResponse response, String message, String memoryId) {
        response.setCharacterEncoding("UTF-8");
        return memoryAiService.chatMemory(memoryId, message);
    }

    @GetMapping("/toolCalling")
    public String toolCalling(HttpServletResponse response, String message) {
        response.setCharacterEncoding("UTF-8");

        LangChainAiService langChainAiService1 = AiServices.builder(LangChainAiService.class)
                .tools(new TemperatureTools())
                .chatModel(chatModel)
                .build();

        return langChainAiService1.chat(message);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        memoryAiService = AiServices.builder(LangChainMemoryAiService.class)
                .chatModel(chatModel)
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.withMaxMessages(10))
              //  .chatMemoryProvider(memoryId -> MessageWindowChatMemory.builder().id(memoryId).maxMessages(10).chatMemoryStore(redisChatMemoryStore).build())
                .build();
    }

}
