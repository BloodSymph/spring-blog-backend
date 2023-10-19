package com.company.blog.service.implementation;

import com.company.blog.domain.UserEntity;

import com.company.blog.dto.user.UserDetailsDto;
import com.company.blog.dto.user.UserDto;


import com.company.blog.mapper.UserMapper;
import com.company.blog.repository.RoleRepository;
import com.company.blog.repository.UserRepository;
import com.company.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


import static com.company.blog.mapper.UserMapper.mapToUserDetailsDto;


@Service
public class UserServiceImplementation implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;


    @Autowired
    public UserServiceImplementation(
            UserRepository userRepository,
            RoleRepository roleRepository
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public List<UserDto> getAllUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<UserEntity> users = userRepository.findAll(pageable);
        List<UserEntity> listOfUsers = users.getContent();
        return listOfUsers.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public UserDetailsDto getUser(String username) {
        UserEntity user = userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Username not found")
                );
        return mapToUserDetailsDto(user);
    }

    @Override
    public List<UserDto> searchUserByUsernameOrEmail(String username, String email) {
        List<UserEntity> users = userRepository.searchByUsernameOrEmailIgnoreCase(username, email);
        return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
    }

    @Override
    public void deleteUser(String username) {
        if (!userRepository.existsByUsernameIgnoreCase(username)) {
            throw new UsernameNotFoundException("Can not delete user by current username");
        }
        userRepository.deleteByUsernameIgnoreCase(username);
    }
}
