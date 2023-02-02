package com.moruflix.movies.application.port.out;

import com.moruflix.movies.domain.Movie;
import com.moruflix.commons.domain.filters.Filter;

import java.util.List;


public interface MovieRepository {

    Movie save(Movie company);
   List<Movie> findAll(List<Filter> filters);
    Movie findById(Integer companyId);
    Movie patch(Movie movie);
    Movie update(Movie movie);
}
