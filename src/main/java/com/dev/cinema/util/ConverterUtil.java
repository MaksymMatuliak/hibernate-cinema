package com.dev.cinema.util;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.CinemaHallRequestDto;
import com.dev.cinema.model.dto.CinemaHallResponseDto;
import com.dev.cinema.model.dto.MovieRequestDto;
import com.dev.cinema.model.dto.MovieResponseDto;
import com.dev.cinema.model.dto.MovieSessionRequestDto;
import com.dev.cinema.model.dto.MovieSessionResponseDto;
import com.dev.cinema.model.dto.OrderResponseDto;
import com.dev.cinema.model.dto.TicketResponseDto;
import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConverterUtil {
    @Autowired
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;

    public ConverterUtil(CinemaHallService cinemaHallService, MovieService movieService) {
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
    }

    public CinemaHallResponseDto convertCinemaHallIntoCinemaHallResponseDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setCinemaId(cinemaHall.getCinemaHallId());
        cinemaHallResponseDto.setDescription(cinemaHall.getDescription());
        cinemaHallResponseDto.setCapacity(cinemaHall.getCapacity());
        return cinemaHallResponseDto;
    }

    public MovieResponseDto convertMovieIntoMovieResponseDto(Movie movie) {
        MovieResponseDto movieResponseDto = new MovieResponseDto();
        movieResponseDto.setMovieId(movie.getMovieId());
        movieResponseDto.setDescription(movie.getDescription());
        movieResponseDto.setTitle(movie.getTitle());
        return movieResponseDto;
    }

    public Movie convertMovieRequestDtoIntoMovie(MovieRequestDto movieRequestDto) {
        Movie movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDescription(movieRequestDto.getDescription());
        return movie;
    }

    public MovieSessionResponseDto convertMovieSessionIntoMovieSessionResponseDto(
            MovieSession movieSession) {
        MovieSessionResponseDto movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getCinemaHallId());
        movieSessionResponseDto.setMovieSessionId(movieSession.getMovieSessionId());
        movieSessionResponseDto.setMovieTitle(movieSession.getMovie().getTitle());
        movieSessionResponseDto.setTime(movieSession.getTime());
        return movieSessionResponseDto;
    }

    public MovieSession convertMovieSessionRequestDtoIntoMovieSession(
            MovieSessionRequestDto movieSessionRequestDto) {
        MovieSession movieSession = new MovieSession();
        movieSession.setCinemaHall(
                cinemaHallService.getById(movieSessionRequestDto.getCinemaHallId()).get());
        movieSession.setTime(LocalDateTime.parse(
                movieSessionRequestDto.getTime(), DateTimeFormatter.ofPattern("yyyyMMddHHmm")));
        movieSession.setMovie(movieService.getById(movieSessionRequestDto.getMovieId()).get());
        return movieSession;
    }

    public CinemaHall convertCinemaHallRequestDtoIntoCinemaHall(
            CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        return cinemaHall;
    }

    public UserResponseDto convertUserIntoUserResponseDto(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setName(user.getName());
        userResponseDto.setId(user.getUserId());
        return userResponseDto;
    }

    public TicketResponseDto convertTicketIntoTicketResponseDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setMovieSessionResponseDto(
                convertMovieSessionIntoMovieSessionResponseDto(ticket.getMovieSession()));
        ticketResponseDto.setTicketId(ticket.getTicketId());
        ticketResponseDto.setUserResponseDto(convertUserIntoUserResponseDto(ticket.getUser()));
        return ticketResponseDto;
    }

    public OrderResponseDto convertOrderIntoOrderResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setTicketsResponseDto(order.getTickets());
        orderResponseDto.setTime(order.getTime());
        orderResponseDto.setUserId(order.getUser().getUserId());
        return orderResponseDto;
    }
}
