package com.up.upfolio.model.api.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SuccessResponse extends BaseApiResponse {
    private boolean success = true;
}
