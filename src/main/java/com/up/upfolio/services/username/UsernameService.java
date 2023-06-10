package com.up.upfolio.services.username;

import java.util.UUID;

public interface UsernameService {

    String create(UUID userUuid, String newUsername);

    String create(UUID userUuid);

    String update(UUID userUuid, String newUsername);

    UUID resolve(String username);

    String resolve(UUID userUuid);

    static String sanitize(String username) {
        return username.toLowerCase().strip();
    }
}
