package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.ConverterUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;
    private final ConverterUtil converterUtil;

    public UserController(UserService userService, ConverterUtil converterUtil) {
        this.userService = userService;
        this.converterUtil = converterUtil;
    }

    @GetMapping("/user/{email}")
    public UserResponseDto getUser(@PathVariable String email) {
        return converterUtil.convertUserIntoUserResponseDto(userService.findByEmail(email).get());
    }
}
