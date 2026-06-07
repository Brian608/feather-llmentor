package org.feather.llm.rag.es;

import lombok.Data;

import java.util.Map;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.rag.es
 * @className: EsDocumentChunk
 * @author: feather
 * @description:
 * @since: 2026-06-07 5:30 PM
 * @version: 1.0
 */
@Data
public class EsDocumentChunk {

    private String id;
    private String content;
    private Map<String, Object> metadata;
}