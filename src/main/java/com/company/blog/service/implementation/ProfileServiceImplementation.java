package com.company.blog.service.implementation;

import com.company.blog.domain.ProfileEntity;
import com.company.blog.domain.UserEntity;
import com.company.blog.dto.profile.UserProfileDetailsDto;
import com.company.blog.dto.profile.UserProfileDto;
import com.company.blog.mapper.UserProfileMapper;
import com.company.blog.repository.ProfileRepository;
import com.company.blog.repository.UserRepository;
import com.company.blog.service.ProfileService;
import com.company.blog.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.company.blog.mapper.UserProfileMapper.*;

@Service
public class ProfileServiceImplementation implements ProfileService {
    private UserRepository userRepository;
    private ProfileRepository profileRepository;

    @Autowired
    public ProfileServiceImplementation(
            UserRepository userRepository,
            ProfileRepository profileRepository
    ) {
        this.userRepository = userRepository;
        this.profileRepository = profileRepository;
    }

    @Override
    public List<UserProfileDto> findAllProfiles(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<ProfileEntity> userProfiles = profileRepository.findAll(pageable);
        List<ProfileEntity> listOfProfiles = userProfiles.getContent();
        return listOfProfiles.stream().map(UserProfileMapper::mapToProfileDto).collect(Collectors.toList());
    }

    @Override
    public List<UserProfileDto> searchProfile(
            String firstName,
            String lastName,
            String country,
            String city
    ) {
        List<ProfileEntity> profiles = profileRepository
                .findByLastNameOrFirstNameOrCityOrCountryIgnoreCase(
                        firstName, lastName, city, country
                );
        return profiles.stream().map(UserProfileMapper::mapToProfileDto).collect(Collectors.toList());
    }

    @Override
    public UserProfileDto findByUsername(String username) {
        ProfileEntity profile = profileRepository
                .findByUser_Username(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find users profile by username: " + username
                        )
                );
        return mapToProfileDto(profile);
    }

    @Override
    public UserProfileDetailsDto findDetailsByUsername(String username) {
        ProfileEntity profile = profileRepository
                .findByUser_Username(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find users profile by username: " + username
                        )
                );
        return mapToProfileDetailsDto(profile);
    }

    @Override
    public UserProfileDto createProfile(UserProfileDto userProfileDto) {
        String username = SecurityUtil.getSessionUser();
        Optional<UserEntity> user = userRepository.findByUsername(username);
        ProfileEntity profile = mapToProfile(userProfileDto);
        profile.setUser(user.get());
        profileRepository.save(profile);
        return mapToProfileDto(profile);
    }

    @Override
    public UserProfileDto updateProfile(UserProfileDto userProfileDto, String username) {
        ProfileEntity profile = profileRepository
                .findByUser_Username(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Can not find users profile by username: " + username
                        )
                );
        profile.setFirstName(userProfileDto.getFirstName());
        profile.setLastName(userProfileDto.getLastName());
        profile.setCity(userProfileDto.getCity());
        profile.setCountry(userProfileDto.getCountry());
        ProfileEntity updatedProfile = profileRepository.save(profile);
        return mapToProfileDto(updatedProfile);
    }

    @Override
    public void deleteProfile(String username) {
        if(!profileRepository.existsByUser_Username(username)) {
            throw new UsernameNotFoundException(
                    "Can not delete users profile by current username!"
            );
        }
        profileRepository.deleteByUser_Username(username);
    }
}
