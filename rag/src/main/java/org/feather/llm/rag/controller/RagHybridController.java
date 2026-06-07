package org.feather.llm.rag.controller;

import com.alibaba.cloud.ai.transformer.splitter.RecursiveCharacterTextSplitter;
import org.feather.llm.rag.embedding.EmbeddingService;
import org.feather.llm.rag.es.ElasticSearchService;
import org.feather.llm.rag.es.EsDocumentChunk;
import org.feather.llm.rag.reader.DocumentReaderFactory;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.rag.controller
 * @className: RagHybridController
 * @author: feather
 * @description:
 * @since: 2026-06-07 5:38 PM
 * @version: 1.0
 */
@RestController
@RequestMapping("/rag/hybrid")
public class RagHybridController {
    @Autowired
    private EmbeddingService embeddingService;

    @Autowired
    private DocumentReaderFactory selector;

    @Autowired
    private ElasticSearchService elasticSearchService;


    @RequestMapping("write")
    public String write(String filePath) throws Exception {
        // 1. 加载文档
        List<Document> documents = selector.read(new File(filePath));

        // 3. 文档分片
        RecursiveCharacterTextSplitter splitter = new RecursiveCharacterTextSplitter(
                // 每块最大字符数
                100,
                // 块之间重叠 100 字符
                new String[]{"。"}
        );

        List<Document> spllittedDocuments = splitter.apply(documents);

        for (Document doc : spllittedDocuments) {
            System.out.println(doc.getText());
            System.out.println(doc.getMetadata());
            System.out.println("--------------------------------------------------");
        }

        //存储到ES
        List<EsDocumentChunk> esDocs = spllittedDocuments.stream().map(doc -> {
            EsDocumentChunk es = new EsDocumentChunk();
            es.setId(doc.getId());
            es.setContent(doc.getText());
            es.setMetadata(doc.getMetadata());
            return es;
        }).toList();

        elasticSearchService.bulkIndex(esDocs);

        //向量化并存储
        embeddingService.embedAndStore(spllittedDocuments);

        return "success";
    }

    @RequestMapping("searchFromEs")
    public List<EsDocumentChunk> searchFromEs(String keyword) throws Exception {
        return elasticSearchService.searchByKeyword(keyword);
    }

    @RequestMapping("searchFromVector")
    public List<Document> searchFromVector(String keyword) throws Exception {
        return embeddingService.similaritySearch(keyword);
    }


}
