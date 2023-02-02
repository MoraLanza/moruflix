package com.moruflix.movies.application.port.in;

import com.moruflix.movies.domain.Movie;
import lombok.Builder;
import lombok.Value;

public interface GetMovieQuery {

        Movie execute(Data data);

        @Value
        @Builder
        class Data {
            Integer movieId;

    }

}
