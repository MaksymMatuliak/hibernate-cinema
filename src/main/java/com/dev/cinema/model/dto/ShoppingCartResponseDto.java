package com.dev.cinema.model.dto;

import java.util.List;

public class ShoppingCartResponseDto {
    private Long shoppingCartId;
    private List<TicketResponseDto> ticketsResponseDto;
    private UserResponseDto userResponseDto;

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public List<TicketResponseDto> getTicketsResponseDto() {
        return ticketsResponseDto;
    }

    public void setTicketsResponseDto(List<TicketResponseDto> ticketsResponseDto) {
        this.ticketsResponseDto = ticketsResponseDto;
    }

    public UserResponseDto getUserResponseDto() {
        return userResponseDto;
    }

    public void setUserResponseDto(UserResponseDto userResponseDto) {
        this.userResponseDto = userResponseDto;
    }
}
