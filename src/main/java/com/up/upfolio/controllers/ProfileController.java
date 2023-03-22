package com.up.upfolio.controllers;

import com.up.upfolio.entities.UserRealNameModel;
import com.up.upfolio.model.api.response.profile.GetMeResponse;
import com.up.upfolio.model.api.response.profile.GetProfileResponse;
import com.up.upfolio.model.user.ProfileModel;
import com.up.upfolio.model.user.ProfileStatus;
import com.up.upfolio.model.user.ProfileType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/profile")
public class ProfileController extends BaseController {
    @GetMapping("/getMe")
    public GetMeResponse getMe() {
        // TODO implement the profile CRUD

        return new GetMeResponse("mock", "https://upfolio.ru/mock");
    }

    @GetMapping("/{username}")
    public GetProfileResponse getProfile(@PathVariable String username) {
        // TODO implement the profile CRUD

        ProfileModel model = new ProfileModel();

        model.setStatus(ProfileStatus.LOOKING_FOR_JOB);
        model.setType(ProfileType.PUBLIC);
        model.setBio("Test bio");
        model.setTags(List.of("angular", "react", "nextjs", "frontend"));
        model.setProfilePhotoUrl("https://upfolio.ru/assets/no-img.png");
        model.setVerified(true);
        model.setUsername("mock");
        model.setRealName(new UserRealNameModel("Ivan", "Mockov"));
        model.setDateOfBirth("2000-12-24");
        model.setRegistered(OffsetDateTime.now().minus(Duration.ofDays(10)));

        return new GetProfileResponse(model);
    }
}
