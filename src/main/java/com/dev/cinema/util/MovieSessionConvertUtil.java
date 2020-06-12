package com.dev.cinema.util;

import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieSessionConvertUtil {
    @Autowired
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;

    public MovieSessionConvertUtil(CinemaHallService cinemaHallService, MovieService movieService) {
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
                cinemaHallService.getById(movieSessionRequestDto.getCinemaHallId()).get());
        movieSession.setTime(movieSessionRequestDto.getTime());
        movieSession.setMovie(movieService.getById(movieSessionRequestDto.getMovieId()).get());
        return movieSession;
    }

}
