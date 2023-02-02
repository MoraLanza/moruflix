package com.moruflix.movies.application.usecase;

import com.moruflix.movies.application.port.in.GetMovieQuery;
import com.moruflix.movies.application.port.out.MovieRepository;
import com.moruflix.movies.domain.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class GetMovieUseCase implements GetMovieQuery {

    private final MovieRepository  movieRepository;

    public GetMovieUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie execute(Data data) {
        log.info("Executing get Movie use case with data {}", data);
        Movie movieResult = movieRepository.findById(data.getMovieId());
        log.info("Movie Repository replied {}", movieResult);
        return movieResult;
    }
}
