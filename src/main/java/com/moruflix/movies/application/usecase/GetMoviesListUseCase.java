package com.moruflix.movies.application.usecase;

import com.moruflix.commons.domain.filters.Filter;
import com.moruflix.commons.domain.filters.FilterBuilder;
import com.moruflix.movies.application.port.in.GetMovieListQuery;
import com.moruflix.movies.application.port.out.MovieRepository;
import com.moruflix.movies.domain.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Slf4j
public class GetMoviesListUseCase implements GetMovieListQuery {
    private final MovieRepository movieRepository;

    public GetMoviesListUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> execute(Data data) {
        log.info("Executing get Movies list use case with data {}", data);
        List<Filter> filters = new FilterBuilder().build(data.getFiltersMap());
        List<Movie> companiesResult = movieRepository.findAll(filters);
        log.info("Movie Repository replied {}", companiesResult);
        return companiesResult;
    }
}
