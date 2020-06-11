package com.dev.cinema.model.dto;

import java.util.List;

public class ShoppingCartRequestDto {
    private Long shoppingCartId;
    private List<TicketRequestDto> ticketsRequestDto;
    private Long userId;

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public List<TicketRequestDto> getTicketsRequestDto() {
        return ticketsRequestDto;
    }

    public void setTicketsRequestDto(List<TicketRequestDto> ticketsRequestDto) {
        this.ticketsRequestDto = ticketsRequestDto;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
