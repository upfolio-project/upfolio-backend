package com.up.upfolio.model.api.response.metrics;

import com.up.upfolio.model.api.response.BaseApiResponse;
import com.up.upfolio.model.metrics.SiteEntitiesCount;
import lombok.*;

@Getter
@RequiredArgsConstructor
public class SiteEntitiesCountResponse extends BaseApiResponse {
    private final SiteEntitiesCount entities;
}
