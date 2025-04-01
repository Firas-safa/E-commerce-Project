package com.exam.project.security;

import com.exam.project.entity.Role;
import com.exam.project.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role CreateNewRole(Role role) {
        return roleRepository.save(role);
    }
}
