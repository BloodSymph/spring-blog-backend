package com.company.blog.mapper;

import com.company.blog.domain.UserEntity;
import com.company.blog.dto.user.UserDetailsDto;
import com.company.blog.dto.user.UserDto;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

import static com.company.blog.mapper.CommentMapper.mapToCommentDto;
import static com.company.blog.mapper.RoleMapper.mapToRole;
import static com.company.blog.mapper.RoleMapper.mapToRoleDto;
import static com.company.blog.mapper.UserProfileMapper.mapToProfileDto;


@Component
public class UserMapper {
    public static UserDto mapToUserDto(UserEntity user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(RoleMapper::mapToRoleDto).collect(Collectors.toList()))
                .created(user.getCreated())
                .updated(user.getUpdated())
                .build();
    }
    public static UserDetailsDto mapToUserDetailsDto(UserEntity user) {
        return UserDetailsDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles().stream().map(RoleMapper::mapToRoleDto).collect(Collectors.toList()))
                .comments(user.getComments().stream().map(CommentMapper::mapToCommentDto).collect(Collectors.toList()))
                .profile(mapToProfileDto(user.getProfile()))
                .created(user.getCreated())
                .updated(user.getUpdated())
                .build();
    }

    public static UserEntity mapToUser(UserDto userDto) {
        return UserEntity.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .roles(userDto.getRoles().stream().map(roleDto -> mapToRole(roleDto)).collect(Collectors.toList()))
                .created(userDto.getCreated())
                .updated(userDto.getUpdated())
                .build();
    }
}
