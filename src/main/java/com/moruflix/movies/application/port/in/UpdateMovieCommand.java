package com.moruflix.movies.application.port.in;

import com.moruflix.movies.domain.Movie;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

public interface UpdateMovieCommand {
    Movie execute(Data data);

    @Value
    @Builder
    class Data {

        Integer movieId;
        String title;
        Double popularity;
        Date releaseDate;
        Integer runtime;
        String resume;

        public Movie toDomain() {
            return Movie.builder()
                    .movieId(movieId)
                    .title(title)
                    .popularity(popularity)
                    .releaseDate(releaseDate)
                    .runtime(runtime)
                    .resume(resume)
                    .build();
        }
    }
}
