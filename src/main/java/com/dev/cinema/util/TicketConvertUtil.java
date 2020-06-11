package com.dev.cinema.util;

import com.dev.cinema.model.Ticket;
import com.dev.cinema.model.dto.TicketResponseDto;
import com.dev.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TicketConvertUtil {
    @Autowired
    private final MovieSessionConvertUtil movieSessionConvertUtil;
    private final UserConvertUtil userConvertUtil;

    public TicketConvertUtil(MovieSessionConvertUtil movieSessionConvertUtil, UserConvertUtil userConvertUtil) {
        this.movieSessionConvertUtil = movieSessionConvertUtil;
        this.userConvertUtil = userConvertUtil;
    }

    public TicketResponseDto convertTicketIntoTicketResponseDto(Ticket ticket) {
        TicketResponseDto ticketResponseDto = new TicketResponseDto();
        ticketResponseDto.setMovieSessionResponseDto(
                movieSessionConvertUtil.convertMovieSessionIntoMovieSessionResponseDto(ticket.getMovieSession()));
        ticketResponseDto.setTicketId(ticket.getTicketId());
        ticketResponseDto.setUserResponseDto(userConvertUtil.convertUserIntoUserResponseDto(ticket.getUser()));
        return ticketResponseDto;
    }
}
