package com.dev.cinema.controllers;

import com.dev.cinema.model.CinemaHall;
import com.dev.cinema.model.dto.CinemaHallRequestDto;
import com.dev.cinema.model.dto.CinemaHallResponseDto;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.util.ConverterUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    private final ConverterUtil converterUtil;

    public CinemaHallController(CinemaHallService cinemaHallService, ConverterUtil converterUtil) {
        this.cinemaHallService = cinemaHallService;
        this.converterUtil = converterUtil;
    }

    @PostMapping("/cinema-halls")
    public void addCinemaHall(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        cinemaHallService.add(
                converterUtil.convertCinemaHallRequestDtoIntoCinemaHall(cinemaHallRequestDto));
    }

    @GetMapping("/cinema-halls")
    public List<CinemaHallResponseDto> getCinemaHalls() {
        List<CinemaHallResponseDto> CinemaHallsResponseDto = new ArrayList<>();
        for (CinemaHall cinemaHall : cinemaHallService.getAll()) {
            CinemaHallsResponseDto.add(
                    converterUtil.convertCinemaHallIntoCinemaHallResponseDto(cinemaHall));
        }
        return CinemaHallsResponseDto;
    }
}
