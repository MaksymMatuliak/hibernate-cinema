package com.dev.cinema.service;

import com.dev.cinema.model.CinemaHall;
import java.util.List;
import java.util.Optional;

public interface CinemaHallService {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();

    Optional<CinemaHall> getById(Long cinemaHallId);
}
