package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.TicketResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.TicketConvertUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final MovieSessionService movieSessionService;
    private final TicketConvertUtil ticketConvertUtil;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieSessionService movieSessionService,
                                  TicketConvertUtil ticketConvertUtil) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
        this.ticketConvertUtil = ticketConvertUtil;
    }

    @PostMapping("/add-movie-session/{movieSessionId}")
    public void addMovieSessionToShoppingCart(
            @PathVariable Long movieSessionId, @RequestParam Long userId) {
        shoppingCartService.addSession(movieSessionService.getById(movieSessionId).get(),
                userService.getById(userId).get());
    }

    @GetMapping("/by-user")
    public List<TicketResponseDto> getShoppingCart(@RequestParam Long userId) {
        return shoppingCartService.getByUser(userService.getById(userId).get())
                .getTickets()
                .stream().map(ticketConvertUtil::entityToResponseDto)
                .collect(Collectors.toList());
    }
}
