package com.moruflix.movies.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.moruflix.movies.domain.Movie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@AllArgsConstructor
@NoArgsConstructor
public class MovieRestModel {

    private Integer movieId;
    private String title;
    private Double popularity;
    private Date releaseDate;
    private Integer runtime;
    private String resume;

       public static MovieRestModel fromDomain(Movie movie) {
            return MovieRestModel.builder()
                    .movieId(movie.getMovieId())
                    .title(movie.getTitle())
                    .popularity(movie.getPopularity())
                    .releaseDate(movie.getReleaseDate())
                    .runtime(movie.getRuntime())
                    .resume(movie.getResume())
                    .build();
        }

        public static List<MovieRestModel> fromListDomain(List<Movie> companies) {
            return companies.stream()
                    .map(MovieRestModel::fromDomain)
                    .collect(Collectors.toList());
        }
    }

