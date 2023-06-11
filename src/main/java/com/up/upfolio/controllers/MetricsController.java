package com.up.upfolio.controllers;

import com.up.upfolio.model.api.response.metrics.SiteEntitiesCountResponse;
import com.up.upfolio.services.metrics.MetricsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/metrics")
@RequiredArgsConstructor
public class MetricsController extends BaseController {
    private final MetricsService metricsService;

    @GetMapping("/getSiteEntitiesCount")
    public SiteEntitiesCountResponse getSiteEntitiesCount() {
        return new SiteEntitiesCountResponse(metricsService.getSiteEntitiesCount());
    }
}
