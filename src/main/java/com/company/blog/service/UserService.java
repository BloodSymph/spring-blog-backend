package com.company.blog.service;

import com.company.blog.dto.authentication.SignupDto;
import com.company.blog.dto.user.UserDetailsDto;
import com.company.blog.dto.user.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<UserDto> getAllUsers(int pageNumber, int pageSize);
    UserDetailsDto getUser(String username);
    List<UserDto> searchUserByUsernameOrEmail(String username, String email);
    void deleteUser(String username);
}
