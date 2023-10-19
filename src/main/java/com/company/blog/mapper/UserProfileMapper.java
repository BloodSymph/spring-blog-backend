package com.company.blog.mapper;

import com.company.blog.domain.ProfileEntity;
import com.company.blog.dto.profile.UserProfileDetailsDto;
import com.company.blog.dto.profile.UserProfileDto;
import org.springframework.stereotype.Component;

import static com.company.blog.mapper.UserMapper.mapToUserDto;

@Component
public class UserProfileMapper {
    public static UserProfileDto mapToProfileDto(ProfileEntity profile) {
        return UserProfileDto.builder()
                .id(profile.getId())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .country(profile.getCountry())
                .city(profile.getCity())
                .build();
    }

    public static UserProfileDetailsDto mapToProfileDetailsDto(ProfileEntity profile) {
        return UserProfileDetailsDto.builder()
                .id(profile.getId())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .country(profile.getCountry())
                .city(profile.getCity())
                .created(profile.getCreated())
                .updated(profile.getUpdated())
                .user(mapToUserDto(profile.getUser()))
                .build();
    }

    public static ProfileEntity mapToProfile(UserProfileDto profileDto) {
        return ProfileEntity.builder()
                .id(profileDto.getId())
                .firstName(profileDto.getFirstName())
                .lastName(profileDto.getLastName())
                .country(profileDto.getCountry())
                .city(profileDto.getCity())
                .build();
    }
}
