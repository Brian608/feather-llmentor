package org.feather.llm.rag.controller;

import org.feather.llm.rag.reader.DocumentReaderFactory;
import org.springframework.ai.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.rag.controller
 * @className: RagReaderController
 * @author: feather
 * @description:
 * @since: 2026-05-09 10:43 AM
 * @version: 1.0
 */
@RestController
@RequestMapping("/rag")
public class RagReaderController {

    @Autowired
    private DocumentReaderFactory documentReaderFactory;

    @GetMapping("/read")
    public String read(String path)  {
     List<Document> documentList;
        try {
            documentList = documentReaderFactory.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (Document document : documentList) {
            System.out.println(document.getText());
            System.out.println(document.getMetadata());
        }
        return "success";
    }
}
