package org.feather.llm.springai.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

import java.math.BigDecimal;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.springai.model
 * @className: Book
 * @author: feather
 * @description:
 * @since: 2026-04-09 9:36 PM
 * @version: 1.0
 */
public record Book(@JsonPropertyDescription("书名，以中文展示") String name,
                   @JsonPropertyDescription("作者") String author,
                   @JsonPropertyDescription("简介") String desc,
                   @JsonPropertyDescription("价格，人民币，以分为单位") BigDecimal price,
                   @JsonPropertyDescription("出版社") String publisher) {

}