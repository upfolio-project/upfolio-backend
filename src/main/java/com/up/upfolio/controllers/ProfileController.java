package com.up.upfolio.controllers;

import com.up.upfolio.model.api.response.profile.GetMeResponse;
import com.up.upfolio.model.api.response.profile.GetProfileResponse;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/profile")
public class ProfileController extends BaseController {
    @GetMapping("/getMe")
    public GetMeResponse getMe() {
        throw new NotImplementedException();
    }

    @GetMapping("/{username}")
    public GetProfileResponse getProfile(@PathVariable String username) {
        throw new NotImplementedException();
    }
}
