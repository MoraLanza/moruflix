package com.moruflix.movies.domain;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.util.Date;

@Value
@Builder
public class Movie {

        @With
        Integer movieId;
        String title;
        Double popularity;
        Date releaseDate;
        Integer runtime;
        String resume;

}
