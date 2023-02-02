package com.moruflix.movies.application.port.in;

import com.moruflix.movies.domain.Movie;
import lombok.Builder;
import lombok.Value;

import java.util.Date;

public interface CreateMovieCommand {
   Movie execute(Data data);

    @Value
    @Builder
    class Data {
        String title;
        Double popularity;
        Date releaseDate;
        Integer runtime;
        String resume;

        public Movie toDomain() {
            return Movie.builder()
                    .title(title)
                    .popularity(popularity)
                    .releaseDate(releaseDate)
                    .runtime(runtime)
                    .resume(resume)
                    .build();
        }
    }


}
