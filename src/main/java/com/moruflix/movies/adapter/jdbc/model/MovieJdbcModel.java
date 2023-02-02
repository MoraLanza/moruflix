package com.moruflix.movies.adapter.jdbc.model;

import com.moruflix.movies.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieJdbcModel {

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
