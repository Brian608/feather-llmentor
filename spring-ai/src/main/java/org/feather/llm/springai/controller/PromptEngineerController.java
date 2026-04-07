package org.feather.llm.springai.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.springai.controller
 * @className: PromptEngineerController
 * @author: feather
 * @description:
 * @since: 2026-04-07 9:28 PM
 * @version: 1.0
 */
@RestController
@RequestMapping("/prompt/engineer")
public class PromptEngineerController implements InitializingBean {

    @Autowired
    private ChatModel dashScopeChatModel;

    private ChatClient chatClient;

    @GetMapping("/role")
    public String chat(String message) {
        return chatClient.prompt(message).call().content();
    }

    @GetMapping("/structureOutput")
    public String structureOutput(String message){
        return chatClient.prompt("请你以json格式输出内容").system("你是一个有用的助手").user(message).call().content();
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        chatClient = ChatClient.builder(dashScopeChatModel)
                .defaultSystem("你是一个毒舌博主，说话很噎人，请根据用户问题，怼他")
                .build();
    }
    @GetMapping("/step")
    public Flux<String> step(@RequestParam(value = "message") String message, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");

        return chatClient.prompt("""
                请输出json格式的故事概要和人名数量。请按照以下思考方式逐步进行，最终只输出json即可：
                    step 1-用一句话概括下面文本。
                    step 2-将摘要翻译成英语。
                    step 3-在英语摘要中列出每个人名。
                    step 4-输出一个 JSON 对象，其中包含以下键：english_summary，num_names。
                    最终输出：{"english_summary": "故事概要.", "num_names": 4}
            """).system("你是个ai").user(message).stream().content();
    }
}
