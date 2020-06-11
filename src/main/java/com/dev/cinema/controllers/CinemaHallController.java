package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.CinemaHallRequestDto;
import com.dev.cinema.model.dto.CinemaHallResponseDto;
import com.dev.cinema.service.CinemaHallService;
import com.dev.cinema.util.CinemaHallConvertUtil;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    private final CinemaHallConvertUtil cinemaHallConvertUtil;

    public CinemaHallController(CinemaHallService cinemaHallService,
                                CinemaHallConvertUtil cinemaHallConvertUtil) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaHallConvertUtil = cinemaHallConvertUtil;
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addCinemaHall(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        cinemaHallService.add(cinemaHallConvertUtil.requestDtoToEntity(cinemaHallRequestDto));
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CinemaHallResponseDto> getCinemaHalls() {
        return cinemaHallService.getAll()
                .stream()
                .map(cinemaHallConvertUtil::entityToResponseDto)
                .collect(Collectors.toList());
    }
}
