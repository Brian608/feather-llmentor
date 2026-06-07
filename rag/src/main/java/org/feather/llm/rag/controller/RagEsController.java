package org.feather.llm.rag.controller;

import org.feather.llm.rag.cleaner.DocumentCleaner;
import org.feather.llm.rag.es.ElasticSearchService;
import org.feather.llm.rag.es.EsDocumentChunk;
import org.feather.llm.rag.reader.DocumentReaderFactory;
import org.feather.llm.rag.spliter.OverlapParagraphTextSplitter;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.rag.controller
 * @className: RagEsController
 * @author: feather
 * @description:
 * @since: 2026-06-07 5:33 PM
 * @version: 1.0
 */
@RestController
@RequestMapping("/rag/es")
public class RagEsController {

    @Autowired
    private DocumentReaderFactory selector;

    @Autowired
    private ElasticSearchService elasticSearchService;

    @GetMapping("/write")
    public String write(String filePath) throws Exception {
        // 1. 加载文档
        List<Document> documents = selector.read(new File(filePath));

        // 2. 文本清洗
        documents = DocumentCleaner.cleanDocuments(documents);

        // 3. 文档分片
        OverlapParagraphTextSplitter splitter = new OverlapParagraphTextSplitter(
                // 每块最大字符数
                200,
                // 块之间重叠 100 字符
                50
        );
        List<Document> apply = splitter.apply(documents);

        // 4. 存储到ES
        List<EsDocumentChunk> esDocs = apply.stream().map(doc -> {
            EsDocumentChunk es = new EsDocumentChunk();
            es.setId(doc.getId());
            es.setContent(doc.getText());
            es.setMetadata(doc.getMetadata());
            return es;
        }).toList();

        elasticSearchService.bulkIndex(esDocs);
        return "success";
    }

    @GetMapping("/search")
    public List<EsDocumentChunk> search(String keyword) throws Exception {
        return elasticSearchService.searchByKeyword(keyword);
    }
}
