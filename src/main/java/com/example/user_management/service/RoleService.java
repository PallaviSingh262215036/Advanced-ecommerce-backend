package com.example.user_management.service;
import com.example.user_management.entity.Role;
// import com.example.user_management.entity.User;
import com.example.user_management.repository.RoleRepository;
// import com.example.user_management.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(String roleName) {
        Role role = new Role(roleName);
        return roleRepository.save(role);
    }
}

