package com.company.blog.service.implementation;

import com.company.blog.domain.RoleEntity;
import com.company.blog.domain.UserEntity;
import com.company.blog.dto.authentication.AuthResponseDto;
import com.company.blog.dto.authentication.LoginDto;
import com.company.blog.dto.authentication.SignupDto;
import com.company.blog.exception.RoleNotFoundException;
import com.company.blog.exception.UsernameIsTakenException;
import com.company.blog.mapper.AuthenticationMapper;
import com.company.blog.repository.RoleRepository;
import com.company.blog.repository.UserRepository;
import com.company.blog.security.JWTGenerator;
import com.company.blog.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

import static com.company.blog.mapper.AuthenticationMapper.mapToSignUpDto;

@Service
public class AuthenticationServiceImplementation implements AuthenticationService {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthenticationServiceImplementation(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder,
            JWTGenerator jwtGenerator
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }


    @Override
    public SignupDto registerNewUser(SignupDto signupDto) {

        if (userRepository.existsByUsername(signupDto.getUsername())) {
            throw new UsernameIsTakenException(
                    "Username: " + signupDto.getUsername() + " is taken!"
            );
        }

        UserEntity user = new UserEntity();

        user.setUsername(signupDto.getUsername());
        user.setEmail(signupDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));

        RoleEntity role = roleRepository
                .findByName("ROLE_USER")
                .orElseThrow(
                        () -> new RoleNotFoundException("Role name dose not exist")
                );
        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);

        return mapToSignUpDto(user);
    }

    @Override
    public AuthResponseDto loginUser(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);

        return new AuthResponseDto(token);
    }
}
