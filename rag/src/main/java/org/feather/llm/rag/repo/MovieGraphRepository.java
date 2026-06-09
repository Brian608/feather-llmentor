package org.feather.llm.rag.repo;

import org.feather.llm.rag.model.DirectorMoviesDto;
import org.feather.llm.rag.model.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.rag.repo
 * @className: MovieGraphRepository
 * @author: feather
 * @description:
 * @since: 2026-06-09 2:48 PM
 * @version: 1.0
 */
@Repository
public interface MovieGraphRepository extends Neo4jRepository<Movie, String> {
    @Query("""
            MATCH (m:Movie {title: $title}) <-[:DIRECTED]- (d:Director) -[:DIRECTED]-> (other:Movie)
            WHERE other.title <> $title
            RETURN d.name AS director, collect(other.title + ' (' + other.year + ')') AS otherMovies
            """)
    List<DirectorMoviesDto> findOtherMoviesBySameDirector(String title);

}
