package com.moruflix.movies.application.usecase;

import com.moruflix.movies.application.port.in.PatchMovieCommand;
import com.moruflix.movies.application.port.out.MovieRepository;
import com.moruflix.movies.domain.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class PatchMovieUseCase implements PatchMovieCommand {

    public final MovieRepository movieRepository;


    public PatchMovieUseCase(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Movie execute(Data data) {
        log.info("Executing patch Movie use case with data {}", data);
        Movie movieResult = movieRepository.patch(data.toDomain());
        log.info("Movie Repository replied {}", movieResult);
        return  movieResult;
    }
}
