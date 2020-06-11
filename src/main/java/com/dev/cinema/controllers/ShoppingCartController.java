package com.dev.cinema.controllers;

import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.TicketResponseDto;
import com.dev.cinema.service.MovieSessionService;
import com.dev.cinema.service.ShoppingCartService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.ConverterUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;
    private final MovieSessionService movieSessionService;
    private final ConverterUtil converterUtil;

    public ShoppingCartController(ShoppingCartService shoppingCartService,
                                  UserService userService,
                                  MovieSessionService movieSessionService,
                                  ConverterUtil converterUtil) {
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
        this.movieSessionService = movieSessionService;
        this.converterUtil = converterUtil;
    }

    @PostMapping("/shopping-carts/add-movie-session/{movieSessionId}")
    public void addMovieSessionToShoppingCart(
            @PathVariable Long movieSessionId, @RequestParam Long userId) {
        shoppingCartService.addSession(movieSessionService.getById(movieSessionId).get(),
                userService.getById(userId).get());
    }

    @GetMapping("/shopping-carts/by-user")
    public List<TicketResponseDto> getShoppingCart(@RequestParam Long userId) {
        List<TicketResponseDto> ticketsDto = new ArrayList<>();
        for (Ticket ticket : shoppingCartService.getByUser(
                userService.getById(userId).get()).getTickets()) {
            ticketsDto.add(converterUtil.convertTicketIntoTicketResponseDto(ticket));
        }
        return ticketsDto;
    }
}
