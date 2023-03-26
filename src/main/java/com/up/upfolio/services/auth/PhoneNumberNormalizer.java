package com.up.upfolio.services.auth;

import com.up.upfolio.exceptions.ErrorBulk;
import com.up.upfolio.exceptions.GenericApiErrorException;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberNormalizer {
    public String normalize(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 6 || phoneNumber.length() > 15 || !phoneNumber.matches("^7\\d+$"))
            throw new GenericApiErrorException(ErrorBulk.BAD_PHONE_NUMBER);

        // todo
        return phoneNumber;
    }
}
