package com.up.upfolio.services.auth;

import com.up.upfolio.exceptions.GenericApiErrorException;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberNormalizer {
    public String normalize(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 6 || phoneNumber.length() > 15)
            throw new GenericApiErrorException("Bad phone number");

        // todo
        return phoneNumber;
    }
}
