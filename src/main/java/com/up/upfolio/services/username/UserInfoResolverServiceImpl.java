package com.up.upfolio.services.username;

import com.up.upfolio.exceptions.ErrorDescriptor;
import com.up.upfolio.exceptions.GenericApiErrorException;
import com.up.upfolio.model.api.response.UserInfoResponse;
import com.up.upfolio.model.api.response.profile.GetMeResponse;
import com.up.upfolio.model.user.UserType;
import com.up.upfolio.repositories.UserRepository;
import com.up.upfolio.services.organization.OrganizationService;
import com.up.upfolio.services.profile.SpecialistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class UserInfoResolverServiceImpl implements UserInfoResolverService {
    private final UsernameService usernameService;
    private final OrganizationService organizationService;
    private final SpecialistService specialistService;
    private final UserRepository userRepository;

    @Value("${upfolio.host:https://upfolio.ru}")
    private String baseHost;

    @Override
    public UserInfoResponse resolve(UUID requestedBy, String username) {
        UUID userUuid = usernameService.resolve(username);
        if (userUuid == null)
            throw new GenericApiErrorException(ErrorDescriptor.ACCOUNT_NOT_FOUND);

        UserType type = userRepository.findById(userUuid).orElseThrow(() -> new GenericApiErrorException(ErrorDescriptor.ACCOUNT_NOT_FOUND)).getUserType();
        UserInfoResponse response = new UserInfoResponse(type, username, userUuid);

        switch (type) {
            case SPECIALIST -> response.setSpecialist(specialistService.getByUuid(requestedBy, userUuid));
            case ORGANIZATION -> response.setOrganization(organizationService.getByUuid(requestedBy, false));
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }

        return response;
    }

    @Override
    public GetMeResponse getMe(UUID requestedBy) {
        String username = usernameService.resolve(requestedBy);

        return new GetMeResponse(username, baseHost + "/" + username);
    }
}
