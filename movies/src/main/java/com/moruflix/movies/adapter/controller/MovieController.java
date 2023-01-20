package com.moruflix.movies.adapter.controller;

import com.moruflix.movies.application.port.in.GetMovieQuery;
import org.springframework.web.bind.annotation.GetMapping;

public class MovieController {

    private final GetMovieQuery getMovieQuery;

    public MovieController(GetMovieQuery getMovieQuery){
        this.getMovieQuery = getMovieQuery;
    }
}

@GetMapping("/{movie_id}")

