package com.dev.cinema;

import com.dev.cinema.config.AppConfig;
import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.Movie;
import com.dev.cinema.model.MovieSession;
import com.dev.cinema.model.Order;
import com.dev.cinema.model.ShoppingCart;
import com.dev.cinema.model.User;
import com.dev.cinema.security.AuthenticationService;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.service.MovieService;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.ShoppingCartService;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        MovieService movieService = context.getBean(MovieService.class);
        Movie movie = new Movie();
        movie.setTitle("Very interesting film");
        movie.setDescription("It's very interesting film maybe");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        CinemaHallService cinemaHallService = context.getBean(CinemaHallService.class);
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(200);
        cinemaHall.setDescription("Bid and pretty hall");
        cinemaHallService.add(cinemaHall);
        cinemaHallService.getAll().forEach(System.out::println);

        MovieSession movieSession = new MovieSession();
        movieSession.setTime(LocalDateTime.of(2020, Month.JUNE, 20, 19, 30));
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        MovieSessionService movieSessionService = context.getBean(MovieSessionService.class);
        movieSessionService.add(movieSession);
        System.out.println(movieSessionService.findAvailableSessions(
                movie.getMovieId(), LocalDate.of(2020, Month.JUNE, 20)));

        AuthenticationService authenticationService = context.getBean(AuthenticationService.class);
        User user = authenticationService.register("maksym@gmail.com", "Maks", "1234");
        authenticationService.register("iv@gmail.com", "Ivan", "1234");
        try {
            System.out.println(authenticationService.login("maksym@gmail.com", "1234").toString());
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        ShoppingCartService shoppingCartService = context.getBean(ShoppingCartService.class);
        shoppingCartService.addSession(movieSession, user);
        ShoppingCart shoppingCart = shoppingCartService.getByUser(user);
        System.out.println(shoppingCart);

        OrderService orderService = context.getBean(OrderService.class);
        Order order = orderService.completeOrder(shoppingCart.getTickets(), user);
        System.out.println(order);
        orderService.getOrderHistory(user).stream().forEach(System.out::println);
        context.close();
    }
}
