package com.dev.cinema.util;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.dto.CinemaHallRequestDto;
import com.dev.cinema.model.dto.CinemaHallResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CinemaHallConvertUtil {
    public CinemaHallResponseDto entityToResponseDto(CinemaHall cinemaHall) {
        CinemaHallResponseDto cinemaHallResponseDto = new CinemaHallResponseDto();
        cinemaHallResponseDto.setCinemaId(cinemaHall.getCinemaHallId());
        cinemaHallResponseDto.setDescription(cinemaHall.getDescription());
        cinemaHallResponseDto.setCapacity(cinemaHall.getCapacity());
        return cinemaHallResponseDto;
    }

    public CinemaHall requestDtoToEntity(CinemaHallRequestDto cinemaHallRequestDto) {
        CinemaHall cinemaHall = new CinemaHall();
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        return cinemaHall;
    }
}
