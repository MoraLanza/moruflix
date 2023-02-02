package com.moruflix.movies.adapter.controller.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.moruflix.movies.config.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMovieRequestBody {
    @Schema(title = "Movie title")
    @Size(min = 3, max = 255, message = ErrorCode.Constants.INVALID_TITLE)
    @NotNull(message = ErrorCode.Constants.INVALID_TITLE)
    private String title;

    @Schema(title = "Movie runtime")
    @Size(min = 90, max = 240 , message = ErrorCode.Constants.INVALID_RUNTIME)
    @NotNull(message = ErrorCode.Constants.INVALID_RUNTIME)
    private int runtime;

    @Schema(title = "Movie resume")
    @Size(min = 100, max = 500, message = ErrorCode.Constants.INVALID_RESUME)
    @NotNull(message = ErrorCode.Constants.INVALID_RESUME)
    private String resume;

    @Schema(title = "Movie release date")
    @NotNull(message = ErrorCode.Constants.INVALID_DATE)
    private Date releaseDate;

    @Schema(title = "Movie popularity")
    @Size(min = 1, max = 10, message = ErrorCode.Constants.INVALID_POPULARITY)
    @NotNull(message = ErrorCode.Constants.INVALID_POPULARITY)
    private Double popularity;
}
