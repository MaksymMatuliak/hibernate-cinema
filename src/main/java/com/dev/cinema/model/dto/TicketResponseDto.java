package com.dev.cinema.model.dto;

public class TicketResponseDto {
    private Long ticketId;
    private UserResponseDto userResponseDto;
    private MovieSessionResponseDto movieSessionResponseDto;

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public UserResponseDto getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserResponseDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }

    public MovieSessionResponseDto getMovieSessionResponseDto() {
        return movieSessionResponseDto;
    }

    public void setMovieSessionResponseDto(MovieSessionResponseDto movieSessionResponseDto) {
        this.movieSessionResponseDto = movieSessionResponseDto;
    }
}
