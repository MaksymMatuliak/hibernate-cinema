package com.dev.cinema.service;

import com.dev.cinema.model.Role;
import java.util.Optional;

public interface RoleService {
    Role add(Role role);

    Optional<Role> getRoleByName(String roleName);
}
