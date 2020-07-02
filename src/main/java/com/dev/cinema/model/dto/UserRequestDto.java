package com.dev.cinema.model.dto;

import com.dev.cinema.validation.EmailConstraint;
import com.dev.cinema.validation.PasswordConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@PasswordConstraint
public class UserRequestDto {
    @NotNull
    @Size(min = 2)
    private String name;
    @EmailConstraint
    @NotNull
    private String email;
    @NotNull
    @Size(min = 2, max = 25)
    private String password;
    @NotNull
    private String repeatPassword;
}
