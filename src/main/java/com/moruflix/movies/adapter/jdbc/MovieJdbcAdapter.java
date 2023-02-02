package com.moruflix.movies.adapter.jdbc;

import com.moruflix.commons.adapter.jdbc.model.filters.JdbcFilterBuilder;
import com.moruflix.commons.adapter.jdbc.model.filters.JdbcQueryBuilder;
import com.moruflix.movies.adapter.jdbc.model.MovieJdbcModel;
import com.moruflix.movies.application.port.out.MovieRepository;
import com.moruflix.movies.config.ErrorCode;
import com.moruflix.movies.domain.Movie;
import com.moruflix.commons.adapter.jdbc.SqlReader;
import com.moruflix.commons.adapter.jdbc.model.filters.JdbcFilter;
import com.moruflix.commons.application.exception.EntityConflictException;
import com.moruflix.commons.application.exception.EntityNotFoundException;
import com.moruflix.commons.application.exception.RepositoryNotAvailableException;
import com.moruflix.commons.domain.filters.Filter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Component
@Slf4j
public class MovieJdbcAdapter implements MovieRepository {
    private static final String PATH_GET_MOVIES = "sql/getMovies.sql";
    private static final String PATH_INSERT_MOVIE = "sql/insertMovie.sql";
    private static final String PATH_GET_MOVIE_BY_ID = "sql/getMovieById.sql";
    private static final String PATH_UPDATE_MOVIE = "sql/updateMovie.sql";
    private static final String PATH_PATCH_MOVIE = "sql/patchMovie.sql";
    private static final String MOVIE_ID = "MOVIE_ID";
    private static final String TITLE = "TITLE";
    private static final String POPULARITY = "POPULARITY";
    private static final String RELEASE_DATE = "RELEASE_DATE";
    private static final String RUNTIME = "RUNTIME";
    private static final String RESUME = "RESUME";

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final String getMoviesQuery;
    private final String insertMoviesQuery;
    private final String getMovieByIdQuery;
    private final String updateMovieQuery;
    private final String patchMovieQuery;

    public MovieJdbcAdapter(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.getMoviesQuery = SqlReader.readSql(PATH_GET_MOVIES);
        this.insertMoviesQuery = SqlReader.readSql(PATH_INSERT_MOVIE);
        this.getMovieByIdQuery = SqlReader.readSql(PATH_GET_MOVIE_BY_ID);
        this.updateMovieQuery = SqlReader.readSql(PATH_UPDATE_MOVIE);
        this.patchMovieQuery = SqlReader.readSql(PATH_PATCH_MOVIE);

    }

    @Override
    public Movie save(Movie movie) {
        log.info("Saving Movie {}", movie);
        Map<String, Object> params = new HashMap<>();
        params.put(TITLE, movie.getTitle());
        params.put(POPULARITY, movie.getPopularity());
        params.put(RELEASE_DATE, movie.getReleaseDate());
        params.put(RUNTIME, movie.getRuntime());
        params.put(RESUME, movie.getResume());

        printQuery(insertMoviesQuery, params);
        Integer movieId = handleExceptions(() -> jdbcTemplate.queryForObject(insertMoviesQuery, params, Integer.class));

        log.info("Saved Movie with id {}", movieId);
        return movie.withMovieId(movieId);
    }

    @Override
    public List<Movie> findAll(List<Filter> filters) {
        log.info("Finding Movies with filters {}", filters);
        List<JdbcFilter> jdbcFilters = new JdbcFilterBuilder().build(filters);

        String selectQuery = JdbcQueryBuilder.buildSelectQuery(getMoviesQuery, jdbcFilters);
        printQuery(selectQuery, Collections.emptyMap());
        List<MovieJdbcModel> moviesResult = handleExceptions(() -> jdbcTemplate.query(selectQuery, Collections.emptyMap(), new BeanPropertyRowMapper<>(MovieJdbcModel.class)));

        log.info("Found Movies {} filters {}", moviesResult, filters);
        return moviesResult.stream()
                .map(MovieJdbcModel::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Movie findById(Integer movieId) {
        log.info("Finding Movie with id {}", movieId);
        final Map<String, Object> params = new HashMap<>();
        params.put(MOVIE_ID, movieId);

        printQuery(getMovieByIdQuery, params);
        MovieJdbcModel movieResult = handleExceptions(() -> jdbcTemplate.queryForObject(getMovieByIdQuery, params, new BeanPropertyRowMapper<>(MovieJdbcModel.class)));
        log.info("Found Movie {}", movieResult);
        return movieResult.toDomain();
    }

    @Override
    public Movie patch(Movie movie) {
        log.info("Patching Movie with {}", movie);
        final Map<String, Object> params = new HashMap<>();
        params.put(TITLE, movie.getTitle());
        params.put(POPULARITY, movie.getPopularity());
        params.put(RELEASE_DATE, movie.getReleaseDate());
        params.put(RUNTIME, movie.getRuntime());
        params.put(RESUME, movie.getResume());

        printQuery(patchMovieQuery, params);
        MovieJdbcModel MovieResult = handleExceptions(() -> jdbcTemplate.queryForObject(patchMovieQuery, params, new BeanPropertyRowMapper<>(MovieJdbcModel.class)));
        log.info("Patched Movie {}", MovieResult);
        return MovieResult.toDomain();
    }

    @Override
    public Movie update(Movie movie) {
        log.info("Updating Movie with {}", movie);
        final Map<String, Object> params = new HashMap<>();
        params.put(MOVIE_ID, movie.getMovieId());
        params.put(TITLE, movie.getTitle());
        params.put(POPULARITY, movie.getPopularity());
        params.put(RELEASE_DATE, movie.getReleaseDate());
        params.put(RUNTIME, movie.getRuntime());
        params.put(RESUME, movie.getResume());

        printQuery(updateMovieQuery, params);
        handleExceptions(() -> jdbcTemplate.update(updateMovieQuery, params));
        log.info("Updated Movie {}", movie);
        return movie;
    }

    private void printQuery(String query, Map<String, Object> queryParams) {
        log.info("Executing query {} with params {}", query, queryParams);
    }

    private <T> T handleExceptions(Callable<T> callable) {
        try {
            return callable.call();
        } catch (EmptyResultDataAccessException ex) {
            log.error("Error finding Movie", ex);
            throw new EntityNotFoundException(ErrorCode.MOVIE_NOT_FOUND);
        } catch (DataIntegrityViolationException ex) {
            log.error("Error saving Movie", ex);
            throw new EntityConflictException(ErrorCode.MOVIE_CONFLICT);
        } catch (DataAccessException ex) {
            log.error("Unexpected error saving Movie", ex);
            throw new RepositoryNotAvailableException(ErrorCode.MOVIE_REPOSITORY_NOT_AVAILABLE);
        } catch (Exception ex) {
            log.error("Unexpected error saving Movie", ex);
            throw new RuntimeException("Unexpected error");
        }
    }
}
