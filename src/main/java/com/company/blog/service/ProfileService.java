package com.company.blog.service;

import com.company.blog.dto.profile.UserProfileDetailsDto;
import com.company.blog.dto.profile.UserProfileDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProfileService {
    List<UserProfileDto> findAllProfiles(int pageNumber, int pageSize);
    List<UserProfileDto> searchProfile(
            String firstName,
            String lastName,
            String country,
            String city
    );
    UserProfileDto findByUsername(String username);
    UserProfileDetailsDto findDetailsByUsername(String username);
    UserProfileDto createProfile(UserProfileDto userProfileDto);
    UserProfileDto updateProfile(UserProfileDto userProfileDto, String username);
    void deleteProfile(String username);
}
