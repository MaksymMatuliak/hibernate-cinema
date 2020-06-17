package com.dev.cinema.controllers;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InjectDataController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void inject() {
        Role userRole = new Role();
        Role adminRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        adminRole.setRoleName(Role.RoleName.ADMIN);
        roleService.add(userRole);
        roleService.add(adminRole);

        User user = new User();
        user.setPassword(passwordEncoder.encode("12345678"));
        user.setName("Maks");
        user.setEmail("maks@gmail.com");
        user.setRoles(Set.of(userRole, adminRole));
        userService.add(user);
    }
}
