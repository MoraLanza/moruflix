package com.moruflix.movies.adapter.controller;

import com.moruflix.movies.adapter.controller.model.CreateMovieRequestBody;
import com.moruflix.movies.adapter.controller.model.MovieRestModel;
import com.moruflix.movies.adapter.controller.model.PatchMovieRequestBody;
import com.moruflix.movies.adapter.controller.model.UpdateMovieRequestBody;
import com.moruflix.movies.application.port.in.*;
import com.moruflix.movies.domain.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/movies")
public class MovieController {

    private final GetMovieQuery getMovieQuery;
    private final CreateMovieCommand createMovieCommand;
    private final UpdateMovieCommand updateMovieCommand;
    private final GetMovieListQuery getMovieListQuery;
    private final PatchMovieCommand patchMovieCommand;

    public MovieController(GetMovieQuery getMovieQuery, CreateMovieCommand createMovieCommand, UpdateMovieCommand updateMovieCommand,
                           GetMovieListQuery getMovieListQuery, PatchMovieCommand patchMovieCommand) {
        this.getMovieQuery = getMovieQuery;
        this.createMovieCommand = createMovieCommand;
        this.updateMovieCommand = updateMovieCommand;
        this.patchMovieCommand = patchMovieCommand;
        this.getMovieListQuery = getMovieListQuery;
    }


    @GetMapping(value = "/{movie_id}")
    // Get Company by Id
    public MovieRestModel getCompany(@PathVariable("movie_id") Integer movieId) {
        log.info("Getting movie with movieId {}", movieId);
        GetMovieQuery.Data data = GetMovieQuery.Data.builder()
                .movieId(movieId)
                .build();
        MovieRestModel movieResult = MovieRestModel.fromDomain(getMovieQuery.execute(data));
        log.info("Get movie with movieId {} replied {}", movieId, movieResult);
        return movieResult;
    }

    @GetMapping
    // Find all
    private ResponseEntity getMovieList(Map<String, String> filtersMap) {
        GetMovieListQuery.Data data = GetMovieListQuery.Data.builder()
                .filtersMap(filtersMap)
                .build();
        List<Movie> movies = getMovieListQuery.execute(data);
        return ResponseEntity.ok(MovieRestModel.fromListDomain(movies));
    }

    @PostMapping
    // Create Movie
    public ResponseEntity<MovieRestModel> createMovie(@RequestBody @Valid CreateMovieRequestBody body) {
        log.info("Creating movie {}", body);
        CreateMovieCommand.Data data = CreateMovieCommand.Data.builder()
                .title(body.getTitle())
                .runtime(body.getRuntime())
                .resume(body.getResume())
                .releaseDate(body.getReleaseDate())
                .popularity(body.getPopularity())
                .build();
        MovieRestModel movieResult = MovieRestModel.fromDomain(createMovieCommand.execute(data));
        log.info("Created movie {}",movieResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieResult);
    }

    @PutMapping(value = "/{movie_id}")
    // Put movie
    public MovieRestModel updateMovie(@PathVariable("movie_id") Integer movieId, @RequestBody @Valid UpdateMovieRequestBody body) {
        log.info("Updating movie with movieId {} with body {}", movieId, body);
        UpdateMovieCommand.Data data = UpdateMovieCommand.Data.builder()
                .movieId(movieId)
                .title(body.getTitle())
                .runtime(body.getRuntime())
                .resume(body.getResume())
                .releaseDate(body.getReleaseDate())
                .popularity(body.getPopularity())
                .build();
        MovieRestModel movieResult = MovieRestModel.fromDomain(updateMovieCommand.execute(data));
        log.info("Movie with movieId {} updated with {}", movieId, movieResult);
        return movieResult;
    }

    @PatchMapping("/{movie_id}")
    // Patch movie with null value
    public MovieRestModel patchMovie(@PathVariable("movie_id") Integer movieId, @RequestBody @Valid PatchMovieRequestBody body) {
        log.info("Patching movie with movieId {} with body {}", movieId, body);
        PatchMovieCommand.Data data = PatchMovieCommand.Data.builder()
                .movieId(movieId)
                .title(body.getTitle())
                .runtime(body.getRuntime())
                .resume(body.getResume())
                .releaseDate(body.getReleaseDate())
                .popularity(body.getPopularity())
                .build();
        MovieRestModel movieResult = MovieRestModel.fromDomain(patchMovieCommand.execute(data));
        log.info("Movie with movieId {} updated with {}", movieId, movieResult);
        return movieResult;
    }
}