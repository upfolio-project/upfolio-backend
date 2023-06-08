package com.up.upfolio.controllers;

import com.up.upfolio.model.api.response.profile.GetMeResponse;
import com.up.upfolio.model.api.response.profile.GetProfileResponse;
import com.up.upfolio.model.profile.InputProfileModel;
import com.up.upfolio.services.profile.ProfileService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/profile")
@RequiredArgsConstructor
public class ProfileController extends BaseController {
    private final ProfileService profileService;

    @GetMapping("/getMe")
    public GetMeResponse getMe(@Parameter(hidden = true) UUID userUuid) {
        return profileService.getMe(userUuid);
    }

    @GetMapping("/user/{username}")
    public GetProfileResponse getProfile(@Parameter(hidden = true) UUID userUuid, @PathVariable String username) {
        return new GetProfileResponse(profileService.getProfile(userUuid, username));
    }

    @PostMapping("/edit")
    public GetProfileResponse editProfile(@Parameter(hidden = true) UUID userUuid, @RequestBody @Valid InputProfileModel inputProfileModel) {
        return new GetProfileResponse(profileService.editProfile(userUuid, inputProfileModel));
    }
}
