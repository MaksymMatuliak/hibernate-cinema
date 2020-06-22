package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;

public class MovieSessionRequestIdDto {
    @NotNull
    private Long movieSessionId;

    public Long getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(Long movieSessionId) {
        this.movieSessionId = movieSessionId;
    }
}
