package com.dev.cinema.model.dto;

import javax.validation.constraints.NotNull;

public class TicketRequestDto {
    @NotNull
    private Long userId;
    @NotNull
    private Long movieSessionId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(Long movieSessionId) {
        this.movieSessionId = movieSessionId;
    }
}
