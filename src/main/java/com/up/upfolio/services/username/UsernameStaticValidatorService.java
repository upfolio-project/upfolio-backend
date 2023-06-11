package com.up.upfolio.services.username;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UsernameStaticValidatorService {
    private static final int MIN_SIZE = 3;
    private static final int MAX_SIZE = 17;

    private static final Set<String> forbiddenNames = Set.of(
            "admin",
            "getMe",
            "edit",
            "search",
            "companies",
            "partners",
            "about",
            "login",
            "register",
            "forgotpassword",
            "index",
            "main",
            "upfolio",
            "assets",
            "complete",
            "amazon",
            "yandex",
            "google",
            "mailru",
            "jetbrains",
            "delmir",
            "emmitrin",
            "create"
    );

    public boolean isValid(String username) {
        if (username.length() < MIN_SIZE || username.length() > MAX_SIZE || forbiddenNames.contains(username.toLowerCase())) return false;

        return username.matches("^\\w+$");
    }
}
