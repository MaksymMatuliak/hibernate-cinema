package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.UserService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev");

    public static void main(String[] args) {
        Movie movie = new Movie();
        movie.setTitle("Very interesting film");
        movie.setDescription("It's very interesting film maybe");
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(200);
        cinemaHall.setDescription("Bid and pretty hall");
        CinemaHallService cinemaHallService =
                (CinemaHallService) injector.getInstance(CinemaHallService.class);
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession = new MovieSession();
        movieSession.setTime(LocalDateTime.of(2020, Month.JUNE, 20, 19, 30));
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        MovieSessionService movieSessionService =
                (MovieSessionService) injector.getInstance(MovieSessionService.class);
        movieSessionService.add(movieSession);
        System.out.println(movieSessionService.findAvailableSessions(
                movie.getMovieId(), LocalDate.of(2020, Month.JUNE, 20)));

        User user = new User();
        user.setEmail("maksym@gmail.com");
        user.setName("Maks");
        user.setPassword("1234");
        User user2 = new User();
        user2.setEmail("iv@gmail.com");
        user2.setName("Ivan");
        user2.setPassword("1234");
        UserService userService = (UserService) injector.getInstance(UserService.class);
        userService.add(user);
        userService.add(user2);
        System.out.println(userService.findByEmail("iv@gmail.com"));
    }
}
