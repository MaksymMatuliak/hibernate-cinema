package com.dev.cinema.model.Dto;

import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.User;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDto {
    private Long shoppingCartId;
    private List<Ticket> tickets = new ArrayList<>();
    private User user;
}
