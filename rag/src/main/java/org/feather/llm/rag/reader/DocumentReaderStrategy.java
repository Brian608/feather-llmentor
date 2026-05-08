package org.feather.llm.rag.reader;

import org.springframework.ai.document.Document;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.rag.reader
 * @className: DocumentReaderStrategy
 * @author: feather
 * @description:
 * @since: 2026-05-08 5:21 PM
 * @version: 1.0
 */
public interface DocumentReaderStrategy {

    /**
     * 判断是否支持该文件
     */
    boolean supports(File file);

    /**
     * 读取文件并返回 Document 列表
     */
    List<Document> read(File file) throws IOException;
}
