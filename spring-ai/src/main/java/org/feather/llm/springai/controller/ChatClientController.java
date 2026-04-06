package org.feather.llm.springai.controller;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.springai.controller
 * @className: ChatClientControltroller
 * @author: feather
 * @description:
 * @since: 2026-04-06 5:57 PM
 * @version: 1.0
 */
@RestController
@RequestMapping("/client")
public class ChatClientController  implements InitializingBean {
    @Autowired
    private ChatModel chatModel;

    private ChatClient chatClient;

    @GetMapping("/simpleCall")
    public String simpleCall(String message) {
        return chatClient.prompt(message).call().content();
    }
    @GetMapping("/call")
    public String call(String message) {
        return chatClient.prompt(new Prompt(new SystemMessage("用英文回答"),new UserMessage(message))).call().content();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        chatClient=ChatClient.builder(chatModel)
                .defaultAdvisors(new SimpleLoggerAdvisor()).
                defaultSystem("用中文回答")
        // 设置 ChatClient 中 ChatModel 的 Options 参数
                .defaultOptions(
                DashScopeChatOptions.builder()
                        .temperature(0.7)
                        .build()
        )
                .build();

    }
}
