package com.up.upfolio.model.api.response.register;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.up.upfolio.model.api.response.SuccessResponse;
import com.up.upfolio.services.auth.RegistrationState;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
public class OtpSentResponse extends SuccessResponse {
    @JsonProperty("confirmationMethod")
    private final RegistrationState.ConfirmationMethod confirmationMethod;

    @JsonProperty("phoneNumber")
    private final String phoneNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OtpSentResponse that = (OtpSentResponse) o;
        return confirmationMethod == that.confirmationMethod && phoneNumber.equals(that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), confirmationMethod, phoneNumber);
    }
}
