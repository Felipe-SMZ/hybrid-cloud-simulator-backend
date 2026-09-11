package com.felipeshimizu.hybridcloudsimulator.controller;

import com.felipeshimizu.hybridcloudsimulator.dto.MetricHistoryResponse;
import com.felipeshimizu.hybridcloudsimulator.dto.MetricLatestResponse;
import com.felipeshimizu.hybridcloudsimulator.service.MetricQueryService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping("/api/metrics")
public class MetricController {

    private final MetricQueryService metricQueryService;

    public MetricController(MetricQueryService metricQueryService) {
        this.metricQueryService = metricQueryService;
    }

    @GetMapping("/latest")
    public List<MetricLatestResponse> findLatest() {
        return metricQueryService.findLatestByEnvironment();
    }

    @GetMapping("/history")
    public List<MetricHistoryResponse> findHistory(@RequestParam(required = false)
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant from,
                                                   @RequestParam(required = false)
                                                   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant to
    ) {
        Instant end = to != null ? to : Instant.now();
        Instant start = from != null ? from : end.minus(2, ChronoUnit.HOURS);

        return metricQueryService.findHistory(start, end);
    }
}