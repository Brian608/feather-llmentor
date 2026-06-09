package org.feather.llm.rag.model;

import java.util.List;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.rag.model
 * @className: DirectorMoviesDto
 * @author: feather
 * @description:
 * @since: 2026-06-09 2:41 PM
 * @version: 1.0
 */
public class DirectorMoviesDto {
    private String director;
    private List<String> otherMovies;

    public DirectorMoviesDto() {
    }

    public DirectorMoviesDto(String director, List<String> otherMovies) {
        this.director = director;
        this.otherMovies = otherMovies;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public List<String> getOtherMovies() {
        return otherMovies;
    }

    public void setOtherMovies(List<String> otherMovies) {
        this.otherMovies = otherMovies;
    }
}