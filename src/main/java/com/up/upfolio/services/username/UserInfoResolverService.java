package com.up.upfolio.services.username;

import com.up.upfolio.model.api.response.UserInfoResponse;
import com.up.upfolio.model.api.response.profile.GetMeResponse;

import java.util.UUID;

public interface UserInfoResolverService {
    UserInfoResponse resolve(UUID requestedBy, String username);

    GetMeResponse getMe(UUID requestedBy);
}
