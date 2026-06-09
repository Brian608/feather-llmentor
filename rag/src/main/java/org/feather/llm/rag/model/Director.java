package org.feather.llm.rag.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.rag.model
 * @className: Director
 * @author: feather
 * @description:
 * @since: 2026-06-09 2:41 PM
 * @version: 1.0
 */
@Node("Director")
public class Director {
    @Id
    private String name;

    public Director() {
    }

    public Director(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}