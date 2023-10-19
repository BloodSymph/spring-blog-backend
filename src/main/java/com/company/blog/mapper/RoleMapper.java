package com.company.blog.mapper;

import com.company.blog.domain.RoleEntity;
import com.company.blog.dto.role.RoleDetailsDto;
import com.company.blog.dto.role.RoleDto;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static com.company.blog.mapper.UserMapper.mapToUserDto;

@Component
public class RoleMapper {
    public static RoleDto mapToRoleDto(RoleEntity role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }

    public static RoleDetailsDto mapToRoleDetailsDto(RoleEntity role) {
        return RoleDetailsDto.builder()
                .id(role.getId())
                .name(role.getName())
                .created(role.getCreated())
                .updated(role.getUpdated())
                .users(role.getUsers().stream().map(UserMapper::mapToUserDto).collect(Collectors.toList()))
                .build();
    }

    public static RoleEntity mapToRole(RoleDto roleDto) {
        return RoleEntity.builder()
                .id(roleDto.getId())
                .name(roleDto.getName())
                .build();
    }
}
