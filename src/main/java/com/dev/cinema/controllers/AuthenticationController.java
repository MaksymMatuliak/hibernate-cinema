package com.dev.cinema.controllers;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.model.dto.UserRequestDto;
import com.dev.cinema.security.AuthenticationService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public void register(@RequestBody @Valid UserRequestDto userRequestDto)
            throws AuthenticationException {
        if (userRequestDto.getPassword().equals(userRequestDto.getRepeatPassword())) {
            authenticationService.register(userRequestDto.getEmail(),
                    userRequestDto.getName(), userRequestDto.getPassword());
        } else {
            throw new AuthenticationException("Passwords are not the same!");
        }
    }
}
