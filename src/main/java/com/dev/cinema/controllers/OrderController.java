package com.dev.cinema.controllers;

import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.OrderRequestDto;
import com.dev.cinema.model.dto.OrderResponseDto;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.OrderConvertUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final OrderConvertUtil orderConvertUtil;

    public OrderController(OrderService orderService, UserService userService,
                           OrderConvertUtil orderConvertUtil) {
        this.orderService = orderService;
        this.userService = userService;
        this.orderConvertUtil = orderConvertUtil;
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        List<Ticket> tickets = new ArrayList<>();
        User user = userService.getById(orderRequestDto.getUserId()).orElseThrow();
        orderService.completeOrder(tickets, user);
    }

    @GetMapping
    public List<OrderResponseDto> getOrderHistory(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return orderService.getOrderHistory(userService.getByEmail(
                userDetails.getUsername()).orElseThrow())
                .stream()
                .map(orderConvertUtil::entityToResponseDto)
                .collect(Collectors.toList());
    }
}
