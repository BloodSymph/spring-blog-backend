package com.company.blog.service;

import com.company.blog.dto.authentication.AuthResponseDto;
import com.company.blog.dto.authentication.LoginDto;
import com.company.blog.dto.authentication.SignupDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    SignupDto registerNewUser(SignupDto signupDto);
    AuthResponseDto loginUser(LoginDto loginDto);
}
