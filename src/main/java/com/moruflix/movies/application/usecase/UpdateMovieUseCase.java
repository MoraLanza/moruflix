package com.moruflix.movies.application.usecase;

import com.moruflix.movies.application.port.in.UpdateMovieCommand;
import com.moruflix.movies.application.port.out.MovieRepository;
import com.moruflix.movies.domain.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdateMovieUseCase implements UpdateMovieCommand {
    public final MovieRepository movieRepository;


    public UpdateMovieUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie execute(Data data) {
        log.info("Executing update Movie use case with data {}", data);
        Movie movieResult = movieRepository.update(data.toDomain());
        log.info("Movie Repository replied {}", movieResult);
        return  movieResult;
    }
}
