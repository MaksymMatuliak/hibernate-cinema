package com.dev.cinema.util;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionConvertUtil {
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;

    public MovieSessionConvertUtil(MovieService movieService, CinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
    }

    public MovieSessionResponseDto entityToResponseDto(MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getCinemaHallId());
        movieSessionResponseDto.setMovieSessionId(movieSession.getMovieSessionId());
        movieSessionResponseDto.setMovieTitle(movieSession.getMovie().getTitle());
        movieSessionResponseDto.setTime(movieSession.getTime());
        return movieSessionResponseDto;
    }

    public MovieSession requestDtoToEntity(MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(
                cinemaHallService.getById(movieSessionRequestDto.getCinemaHallId()).orElseThrow());
        movieSession.setTime(movieSessionRequestDto.getTime());
        movieSession.setMovie(
                movieService.getById(movieSessionRequestDto.getMovieId()).orElseThrow());
        return movieSession;
    }

}
