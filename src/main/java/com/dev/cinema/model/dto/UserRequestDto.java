package com.dev.cinema.model.dto;

import com.dev.cinema.validation.EmailConstraint;
import com.dev.cinema.validation.PasswordConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }
}
