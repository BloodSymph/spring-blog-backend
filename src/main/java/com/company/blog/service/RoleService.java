package com.company.blog.service;

import com.company.blog.dto.role.RoleDetailsDto;
import com.company.blog.dto.role.RoleDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    List<RoleDto> getRoles();
    RoleDetailsDto getRole(String name);
}
