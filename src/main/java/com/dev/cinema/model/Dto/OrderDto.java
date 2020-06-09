package com.dev.cinema.model.Dto;

import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import java.time.LocalDateTime;
import java.util.List;

public class OrderDto {
    private Long orderId;
    private LocalDateTime time;
    private List<Ticket> tickets;
    private User user;
}
