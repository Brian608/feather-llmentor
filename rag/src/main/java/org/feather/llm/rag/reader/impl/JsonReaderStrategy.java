package org.feather.llm.rag.reader.impl;

import org.feather.llm.rag.reader.DocumentReaderStrategy;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.JsonReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.rag.reader.impl
 * @className: JsonReaderStrategy
 * @author: feather
 * @description:
 * @since: 2026-05-08 5:44 PM
 * @version: 1.0
 */
@Service
public class JsonReaderStrategy implements DocumentReaderStrategy {
    @Override
    public boolean supports(File file) {

        String name = file.getName().toLowerCase();
        return name.endsWith(".json");
    }

    @Override
    public List<Document> read(File file) throws IOException {
        Resource resource = new FileSystemResource(file);
        JsonReader jsonReader = new JsonReader(resource);
        return jsonReader.get();
    }
}

