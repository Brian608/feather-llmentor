package org.feather.llm.rag.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.rag.model
 * @className: Movie
 * @author: feather
 * @description:
 * @since: 2026-06-09 2:41 PM
 * @version: 1.0
 */
@Node("Movie")
public class Movie {
    @Id
    private String title;

    private int year;

    public Movie() {
    }

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }
}
