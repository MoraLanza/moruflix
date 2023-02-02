package com.moruflix.movies.application.usecase;

import com.moruflix.movies.application.port.in.CreateMovieCommand;
import com.moruflix.movies.application.port.out.MovieRepository;
import com.moruflix.movies.domain.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreateMovieUseCase implements CreateMovieCommand {

    private final MovieRepository movieRepository;

    public CreateMovieUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie execute(Data data) {
        log.info("Executing create Movie use case with data {}", data);
        Movie movieResult = movieRepository.save(data.toDomain());
        log.info("Movie Repository replied {}", movieResult);
        return movieResult;
    }
}
