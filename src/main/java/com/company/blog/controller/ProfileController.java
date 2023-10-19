package com.company.blog.controller;

import com.company.blog.dto.profile.UserProfileDto;
import com.company.blog.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/home")
public class ProfileController {
    private ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile/{username}")
    public UserProfileDto getUserProfile(@PathVariable(value = "username") String username) {
        return profileService.findByUsername(username);
    }

    @PostMapping("/profile/create")
    public UserProfileDto createProfile(@Valid @RequestBody UserProfileDto userProfileDto) {
        return profileService.createProfile(userProfileDto);
    }

    @PutMapping("/profile/{username}/update")
    public UserProfileDto updateProfile(
            @Valid @RequestBody UserProfileDto userProfileDto,
            @PathVariable(value = "username") String username
    ) {
        return profileService.updateProfile(userProfileDto, username);
    }

    @DeleteMapping("/profile/{username}/delete")
    public String deleteProfile(@PathVariable(value = "username") String username) {
        profileService.deleteProfile(username);
        return "Successful deleted!";
    }
}
