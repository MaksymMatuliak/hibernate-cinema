package com.dev.cinema.security;

import com.dev.cinema.exceptions.AuthenticationException;
import com.dev.cinema.lib.Inject;
import com.dev.cinema.lib.Service;
import com.dev.cinema.model.User;
import com.dev.cinema.service.UserService;
import com.dev.cinema.util.HashUtil;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private UserService userService;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDB = userService.findByEmail(email);
        if (userFromDB.getPassword().equals(
                HashUtil.hashPassword(password, userFromDB.getSalt()))) {
            return userFromDB;
        }
        throw new AuthenticationException("Incorrect login or password");
    }

    @Override
    public User register(String email,String name, String password) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        return userService.add(user);
    }
}
