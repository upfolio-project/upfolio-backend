package com.up.upfolio.model.api.request.register;

import com.up.upfolio.model.api.response.BaseApiResponse;

import com.up.upfolio.model.user.UserType;
import lombok.*;

@Getter
@Setter
public class GetRegisterTokenRequest extends BaseApiResponse {
    private UserType userType = UserType.SPECIALIST;
}
