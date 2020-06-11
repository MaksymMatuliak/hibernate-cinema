package com.dev.cinema.controllers;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import com.dev.cinema.model.dto.OrderRequestDto;
import com.dev.cinema.model.dto.OrderResponseDto;
import com.dev.cinema.service.OrderService;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.ConverterUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final ConverterUtil converterUtil;

    public OrderController(OrderService orderService, UserService userService,
                           ConverterUtil converterUtil) {
        this.orderService = orderService;
        this.userService = userService;
        this.converterUtil = converterUtil;
    }

    @PostMapping("/orders/complete")
    public void completeOrder(@RequestBody OrderRequestDto orderRequestDto) {
        List<Ticket> tickets = new ArrayList<>();
        User user = userService.findByEmail(orderRequestDto.getUserEmail()).get();
        orderService.completeOrder(tickets, user);
    }

    @GetMapping("/orders")
    public List<OrderResponseDto> getOrderHistory(@RequestParam Long userId) {
        List<OrderResponseDto> ordersResponseDto = new ArrayList<>();
        for (Order order : orderService.getOrderHistory(userService.getById(userId).get())) {
            ordersResponseDto.add(converterUtil.convertOrderIntoOrderResponseDto(order));
        }
        return ordersResponseDto;
    }
}
