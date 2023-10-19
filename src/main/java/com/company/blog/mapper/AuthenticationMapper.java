package com.company.blog.mapper;

import com.company.blog.domain.UserEntity;
import com.company.blog.dto.authentication.SignupDto;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationMapper {
    public static SignupDto mapToSignUpDto(UserEntity user) {
        return SignupDto.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .password(user.getPassword())
                .created(user.getCreated())
                .updated(user.getUpdated())
                .build();
    }
}
