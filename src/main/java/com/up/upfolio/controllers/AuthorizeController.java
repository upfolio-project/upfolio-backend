package com.up.upfolio.controllers;

import com.up.upfolio.model.api.request.auth.AuthorizeByPasswordRequest;
import com.up.upfolio.model.api.response.auth.JwtSuccessAuthResponse;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/authorize")
public class AuthorizeController extends BaseController {
    @PostMapping("/byPassword")
    public JwtSuccessAuthResponse byPassword(@RequestBody AuthorizeByPasswordRequest request) {
        throw new NotImplementedException();
    }
}
