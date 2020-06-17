package com.dev.cinema.service.impl;

import com.dev.cinema.dao.RoleDao;
import com.dev.cinema.model.Role;
import com.dev.cinema.service.RoleService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public Role add(Role role) {
        return roleDao.add(role);
    }

    @Override
    public Optional<Role> getRoleByName(String roleName) {
        return roleDao.getRoleByName(roleName);
    }
}
