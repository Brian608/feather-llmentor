package org.feather.llm.rag.reader.impl;

import org.feather.llm.rag.reader.DocumentReaderStrategy;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.TextReader;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.rag.reader.impl
 * @className: TextReaderStrategy
 * @author: feather
 * @description:
 * @since: 2026-05-08 5:22 PM
 * @version: 1.0
 */
@Service
public class TextReaderStrategy implements DocumentReaderStrategy {
    @Override
    public boolean supports(File file) {
        String name = file.getName().toLowerCase();
        return name.endsWith(".txt") || name.endsWith(".tex") || name.endsWith(".text");
    }

    @Override
    public List<Document> read(File file) throws IOException {
        FileSystemResource resource = new FileSystemResource(file);
        TextReader textReader=new TextReader(resource);
        return textReader.get();
    }
}
