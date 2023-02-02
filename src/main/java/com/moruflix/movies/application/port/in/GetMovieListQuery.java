package com.moruflix.movies.application.port.in;

import com.moruflix.movies.domain.Movie;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.Map;

public interface GetMovieListQuery {
    List<Movie> execute(Data data);

    @Value
    @Builder
    class Data {
        Map<String, String> filtersMap;
    }
}
