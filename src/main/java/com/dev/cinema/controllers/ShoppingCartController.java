package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.TicketResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.TicketConvertUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
            @PathVariable Long movieSessionId, Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        shoppingCartService.addSession(movieSessionService.getById(movieSessionId).orElseThrow(),
                userService.findByEmail(userDetails.getUsername()).orElseThrow());
    }

    @GetMapping("/by-user")
    public List<TicketResponseDto> getShoppingCart(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return shoppingCartService.getByUser(userService.findByEmail(
                userDetails.getUsername()).orElseThrow())
                .getTickets()
                .stream().map(ticketConvertUtil::entityToResponseDto)
                .collect(Collectors.toList());
    }
}
