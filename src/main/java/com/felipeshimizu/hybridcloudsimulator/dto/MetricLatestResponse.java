package com.felipeshimizu.hybridcloudsimulator.dto;

import com.felipeshimizu.hybridcloudsimulator.model.enums.CurrencyType;
import com.felipeshimizu.hybridcloudsimulator.model.enums.EnvironmentType;

import java.math.BigDecimal;
import java.time.Instant;

public record MetricLatestResponse(
        Long environmentId,
        String environmentName,
        EnvironmentType environmentType,
        Instant collectedAt,
        int latencyMs,
        BigDecimal errorRatePercent,
        BigDecimal costAmount,
        CurrencyType currency
) {
}
