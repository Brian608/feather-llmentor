package org.feather.llm.rag.controller;

import org.feather.llm.rag.generate.SqlQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.rag.controller
 * @className: RagGenerateController
 * @author: feather
 * @description:
 * @since: 2026-05-19 9:05 PM
 * @version: 1.0
 */
@RestController
@RequestMapping("/rag/generate")
public class RagGenerateController {

    @Autowired
    private SqlQueryService sqlQueryService;

    @RequestMapping("/sql")
    public String sql(String query) {
        return sqlQueryService.text2sql(query);
    }
}