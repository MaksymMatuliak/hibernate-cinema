package com.dev.cinema.util;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.dto.OrderResponseDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderConvertUtil {
    private final TicketConvertUtil ticketConvertUtil;

    public OrderConvertUtil(TicketConvertUtil ticketConvertUtil) {
        this.ticketConvertUtil = ticketConvertUtil;
    }

    public OrderResponseDto entityToResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setTime(order.getTime());
        orderResponseDto.setUserId(order.getUser().getUserId());
        orderResponseDto.setTicketsResponseDto(order.getTickets()
                .stream()
                .map(ticketConvertUtil::entityToResponseDto)
                .collect(Collectors.toList()));
        return orderResponseDto;
    }
}
