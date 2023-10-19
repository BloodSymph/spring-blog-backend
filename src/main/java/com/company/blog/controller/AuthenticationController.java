package com.company.blog.controller;

import com.company.blog.dto.authentication.AuthResponseDto;
import com.company.blog.dto.authentication.LoginDto;
import com.company.blog.dto.authentication.SignupDto;
import com.company.blog.service.AuthenticationService;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public SignupDto signup(@Valid @RequestBody SignupDto signupDto) {
        return authenticationService.registerNewUser(signupDto);
    }

    @PostMapping("/login")
    public AuthResponseDto login(@Valid @RequestBody LoginDto loginDto) {
        return authenticationService.loginUser(loginDto);
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.OK)
    public String logout() {
        return "Successful logout!";
    }
}
