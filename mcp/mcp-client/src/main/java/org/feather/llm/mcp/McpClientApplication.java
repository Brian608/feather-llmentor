package org.feather.llm.mcp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.mcp
 * @className: McpClientApplication
 * @author: feather
 * @description:
 * @since: 2026-05-07 3:45 PM
 * @version: 1.0
 */
@EnableScheduling
@SpringBootApplication
public class McpClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(McpClientApplication.class, args);
    }

}
