package org.feather.llm.langchain4j.domain;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.math.BigDecimal;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.langchain4j.domain
 * @className: Book
 * @author: feather
 * @description:
 * @since: 2026-04-17 2:38 PM
 * @version: 1.0
 */
public record Book(@JsonPropertyDescription("书籍名称") String title,
                   @JsonPropertyDescription("作者") String author,
                   @JsonPropertyDescription("书籍介绍") String description,
                   @JsonPropertyDescription("价格") BigDecimal price) {

}
