package com.dev.cinema.util;

import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.TicketResponseDto;
import org.springframework.stereotype.Component;

@Component
public class TicketConvertUtil {
    private final MovieSessionConvertUtil movieSessionConvertUtil;
    private final UserConvertUtil userConvertUtil;

    public TicketConvertUtil(MovieSessionConvertUtil movieSessionConvertUtil,
                             UserConvertUtil userConvertUtil) {
        this.movieSessionConvertUtil = movieSessionConvertUtil;
        this.userConvertUtil = userConvertUtil;
    }

    public TicketResponseDto entityToResponseDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setMovieSessionResponseDto(
                movieSessionConvertUtil.entityToResponseDto(ticket.getMovieSession()));
        ticketResponseDto.setTicketId(ticket.getTicketId());
        ticketResponseDto.setUserResponseDto(userConvertUtil.entityToResponseDto(ticket.getUser()));
        return ticketResponseDto;
    }
}
