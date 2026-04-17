package org.feather.llm.langchain4j.controller;

import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.langchain4j.controller
 * @className: LangChainLowLevelController
 * @author: feather
 * @description:
 * @since: 2026-04-17 10:08 AM
 * @version: 1.0
 */
@RestController
@RequestMapping("/langchain/low")
public class LangChainLowLevelController {
    @Autowired
    OpenAiChatModel chatModel;




    @GetMapping("/hello")
    public String hello() {
        return chatModel.chat("你好,你是谁？");
    }
}
