package com.up.upfolio.controllers;

import com.up.upfolio.model.api.response.profile.GetSpecialistResponse;
import com.up.upfolio.model.profile.InputSpecialistModel;
import com.up.upfolio.services.profile.SpecialistService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/specialist")
@RequiredArgsConstructor
public class SpecialistController extends BaseController {
    private final SpecialistService specialistService;

    @PostMapping("/edit")
    public GetSpecialistResponse editProfile(@Parameter(hidden = true) UUID userUuid, @Valid @RequestBody InputSpecialistModel inputSpecialistModel) {
        return new GetSpecialistResponse(specialistService.editProfile(userUuid, inputSpecialistModel));
    }
}
