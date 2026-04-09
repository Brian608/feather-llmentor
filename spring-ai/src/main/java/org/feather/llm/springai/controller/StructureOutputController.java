package org.feather.llm.springai.controller;

import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import org.feather.llm.springai.model.Book;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.springai.controller
 * @className: StructureOutputController
 * @author: feather
 * @description:
 * @since: 2026-04-09 9:12 PM
 * @version: 1.0
 */

@RestController
@RequestMapping("/structure")
public class StructureOutputController implements InitializingBean {

    @Autowired
    private ChatModel dashScopeChatModel;

    private ChatClient chatClient;

   @GetMapping("/call")
   public  String call (String message) {
       PromptTemplate promptTemplate = PromptTemplate.builder().template("请给我推荐几本心理学有关的书，输出格式：{format}").build();
       BeanOutputConverter<Book> converter = new BeanOutputConverter<>(Book.class);
       String content = chatClient.prompt(
                       promptTemplate.create(Map.of("format", converter.getFormat())))
               .call().content();
       Book book=converter.convert(content);
       System.out.println(book);
       return  book.name()+" "+book.author()+" "+book.desc()+" "+book.price()+" "+book.publisher();

   }

    @RequestMapping("/convert")
    public String convert() {
        Book book = chatClient.prompt("请给我推荐几本心理学有关的书")
                .call().entity(Book.class);
        System.out.println(book);
        return book.name() + " 、 " + book.author() + " 、 " + book.desc() + " 、 " + book.price() + " 、 " + book.publisher();
    }








    @Override
    public void afterPropertiesSet() throws Exception {
        chatClient = ChatClient.builder(dashScopeChatModel)
                // 实现 Logger 的 Advisor
                .defaultAdvisors(
                        new SimpleLoggerAdvisor()
                )
                // 设置 ChatClient 中 ChatModel 的 Options 参数
                .defaultOptions(
                        DashScopeChatOptions.builder()
                                .temperature(0.7)
                                .build()
                )
                .build();
    }
}
