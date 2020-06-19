package com.dev.cinema.controllers;

import com.dev.cinema.model.dto.UserResponseDto;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.UserConvertUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserConvertUtil userConvertUtil;

    public UserController(UserService userService, UserConvertUtil userConvertUtil) {
        this.userService = userService;
        this.userConvertUtil = userConvertUtil;
    }

    @GetMapping
    public UserResponseDto getUser(@RequestParam String email) {
        return userConvertUtil.entityToResponseDto(userService.findByEmail(email).orElseThrow());
    }
}
