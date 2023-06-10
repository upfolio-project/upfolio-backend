package com.up.upfolio.controllers;

import com.up.upfolio.model.api.response.profile.GetOrganizationResponse;
import com.up.upfolio.model.profile.InputOrganizationModel;
import com.up.upfolio.services.organization.OrganizationService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/v1/organization")
@RequiredArgsConstructor
public class OrganizationController extends BaseController {
    private final OrganizationService organizationService;

    @PostMapping("/edit")
    public GetOrganizationResponse editProfile(@Parameter(hidden = true) UUID userUuid, @Valid @RequestBody InputOrganizationModel inputOrganizationModel) {
        return new GetOrganizationResponse(organizationService.editProfile(userUuid, inputOrganizationModel));
    }
}
