package com.up.upfolio.controllers;

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

    @PostMapping("/edit")
    public GetProfileResponse editProfile(@Parameter(hidden = true) UUID userUuid, @Valid @RequestBody InputProfileModel inputProfileModel) {
        return new GetProfileResponse(profileService.editProfile(userUuid, inputProfileModel));
    }
}
