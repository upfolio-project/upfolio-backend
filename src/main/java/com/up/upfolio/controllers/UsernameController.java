package com.up.upfolio.controllers;

import com.up.upfolio.model.api.request.ChangeUsernameRequest;
import com.up.upfolio.model.api.response.UserInfoResponse;
import com.up.upfolio.model.api.response.SuccessResponse;
import com.up.upfolio.model.api.response.profile.GetMeResponse;
import com.up.upfolio.services.username.UserInfoResolverService;
import com.up.upfolio.services.username.UsernameService;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class UsernameController {
    private final UserInfoResolverService userInfoResolverService;
    private final UsernameService usernameService;

    @GetMapping("/getByUsername/{username}")
    public UserInfoResponse getByUsername(@Parameter(hidden = true) UUID requestedBy, @PathVariable String username) {
        return userInfoResolverService.resolve(requestedBy, username);
    }


    @GetMapping("/changeUsername")
    public SuccessResponse changeUsername(@Parameter(hidden = true) UUID requestedBy, @Valid @RequestBody ChangeUsernameRequest request) {
        usernameService.update(requestedBy, request.getNewUsername());

        return new SuccessResponse();
    }

    @GetMapping("/getMe")
    public GetMeResponse getMe(@Parameter(hidden = true) UUID requestedBy) {
        return userInfoResolverService.getMe(requestedBy);
    }
}
