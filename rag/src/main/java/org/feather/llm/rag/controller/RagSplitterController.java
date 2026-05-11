package org.feather.llm.rag.controller;

import com.alibaba.cloud.ai.transformer.splitter.RecursiveCharacterTextSplitter;
import org.feather.llm.rag.cleaner.DocumentCleaner;
import org.feather.llm.rag.reader.DocumentReaderFactory;
import org.feather.llm.rag.spliter.MarkdownHeaderTextSplitter;
import org.feather.llm.rag.spliter.OverlapParagraphTextSplitter;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.rag.controller
 * @className: RagSplitterController
 * @author: feather
 * @description:
 * @since: 2026-05-09 4:09 PM
 * @version: 1.0
 */
@RestController
@RequestMapping("/rag")
public class RagSplitterController {
    @Autowired
    private DocumentReaderFactory documentReaderFactory;

    @GetMapping("/split")
    public String split(String filePath) {
        List<Document> documents;
        try {
            documents = DocumentCleaner.cleanDocuments(documentReaderFactory.read(new File(filePath)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Document document : documents) {
            System.out.println("before chunk : " + document.getText());
            System.out.println();
            OverlapParagraphTextSplitter tokenTextSplitter = new OverlapParagraphTextSplitter(
                    100,
                    5);

            List<Document> chunkedDocuments = tokenTextSplitter.split(document);

            for (Document chunkedDocument : chunkedDocuments) {
                System.out.println("after chunk : " + chunkedDocument.getText());
                System.out.println();
            }
            System.out.println("==============");
        }
        return "success";
    }

    @RequestMapping("/splitRecursive")
    public String splitRecursive(String filePath) {
        List<Document> documents;
        try {
            documents = documentReaderFactory.read(new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Document document : documents) {
            System.out.println("before chunk : " + document.getText());
            System.out.println();

            RecursiveCharacterTextSplitter splitter = new RecursiveCharacterTextSplitter(300, new String[]{"\n\n", "\n"});

            List<Document> chunkedDocuments = splitter.split(document);

            for (Document chunkedDocument : chunkedDocuments) {
                System.out.println("after chunk : " + chunkedDocument.getText());
                System.out.println();
            }
            System.out.println("==============");
        }
        return "success";
    }

    @GetMapping("/splitParent")
    public String splitParent() {
        MarkdownHeaderTextSplitter markdownHeaderTextSplitter = new MarkdownHeaderTextSplitter(Map.of("#", "一级标题", "##", "二级标题", "###", "三基标题"), false
                , false, true);

        String markdownTest = """
                #哒哒哒
                大叔大大
                
                ## dasdasda
                ### fcsafadfa
                dsada
                dasdas
                dasdsaddsadfwr
                
                ## dsadasd
                ## edawdada
                ### dasdasda
                dadafaf
                
                #### fsdfsfasfsf
                
                # e2ewaeaw
                ## dawdadas
                dsadasd
                ## dsadas
                ## dasda
                dsadad
                ### asfasdada
                ### dadad
                
                Eadsadada
                
                #### dfasda
                daddadsa
                """;
        List<Document> documents = markdownHeaderTextSplitter.split(new Document(markdownTest));
        for (Document document : documents) {
            System.out.println(document.getText());
            System.out.println(document.getMetadata());
            System.out.println("==============");

        }
        return "success";
    }
}
