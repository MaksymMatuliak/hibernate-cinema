package com.dev.cinema.util;

import com.dev.cinema.model.Order;
import com.dev.cinema.model.dto.OrderResponseDto;
import org.springframework.stereotype.Component;

@Component
public class OrderConvertUtil {
    public OrderResponseDto convertOrderIntoOrderResponseDto(Order order) {
        OrderResponseDto orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getOrderId());
        orderResponseDto.setTicketsResponseDto(order.getTickets());
        orderResponseDto.setTime(order.getTime());
        orderResponseDto.setUserId(order.getUser().getUserId());
        return orderResponseDto;
    }
}
