package org.feather.llm.rag.service;

import org.feather.llm.rag.model.DirectorMoviesDto;
import org.feather.llm.rag.repo.MovieGraphRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @projectName: feather-llmentor
 * @package: org.feather.llm.rag.service
 * @className: GraphService
 * @author: feather
 * @description:
 * @since: 2026-06-09 2:59 PM
 * @version: 1.0
 */
@Service
public class GraphService {
    @Autowired
    private MovieGraphRepository repository;

    public String retrieveContext(String movieName) {
        List<DirectorMoviesDto> results = repository.findOtherMoviesBySameDirector(movieName);

        if (results.isEmpty()) {
            return "未找到导演过《" + movieName + "》的导演的其他作品。";
        }

        StringBuilder sb = new StringBuilder();
        for (DirectorMoviesDto dto : results) {
            String director = dto.getDirector();
            List<String> movies = dto.getOtherMovies();
            sb.append(String.format("- 导演 %s 还执导了：%s\n", director, String.join("、", movies)));
        }
        return sb.toString().trim();

    }
}
