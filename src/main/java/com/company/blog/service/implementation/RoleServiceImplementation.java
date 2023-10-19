package com.company.blog.service.implementation;

import com.company.blog.domain.RoleEntity;
import com.company.blog.dto.role.RoleDetailsDto;
import com.company.blog.dto.role.RoleDto;
import com.company.blog.exception.RoleNotFoundException;
import com.company.blog.mapper.RoleMapper;
import com.company.blog.repository.RoleRepository;
import com.company.blog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.company.blog.mapper.RoleMapper.*;

@Service
public class RoleServiceImplementation implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImplementation(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<RoleDto> getRoles() {
        List<RoleEntity> roles = roleRepository.findAll();
        return roles.stream().map(RoleMapper::mapToRoleDto).collect(Collectors.toList());
    }

    @Override
    public RoleDetailsDto getRole(String name) {
        RoleEntity role = roleRepository
                .findByNameIgnoreCase(name)
                .orElseThrow(
                        () -> new RoleNotFoundException("Role can not be found by name: " + name)
                );
        return mapToRoleDetailsDto(role);
    }

}
