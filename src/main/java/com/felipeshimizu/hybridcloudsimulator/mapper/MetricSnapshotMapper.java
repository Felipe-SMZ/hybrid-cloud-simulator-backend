package com.felipeshimizu.hybridcloudsimulator.mapper;

import com.felipeshimizu.hybridcloudsimulator.dto.MetricHistoryResponse;
import com.felipeshimizu.hybridcloudsimulator.dto.MetricLatestResponse;
import com.felipeshimizu.hybridcloudsimulator.model.MetricSnapshot;
import org.springframework.stereotype.Component;

@Component
public class MetricSnapshotMapper {
    public MetricLatestResponse toLatestResponse(MetricSnapshot snapshot) {
        return new MetricLatestResponse(
                snapshot.getEnvironment().getId(),
                snapshot.getEnvironment().getName(),
                snapshot.getEnvironment().getType(),
                snapshot.getCollectedAt(),
                snapshot.getLatencyMs(),
                snapshot.getErrorRatePercent(),
                snapshot.getCostAmount(),
                snapshot.getCurrency()
        );
    }

    public MetricHistoryResponse toHistoryResponse(MetricSnapshot snapshot) {
        return new MetricHistoryResponse(
                snapshot.getEnvironment().getId(),
                snapshot.getEnvironment().getName(),
                snapshot.getEnvironment().getType(),
                snapshot.getCollectedAt(),
                snapshot.getLatencyMs(),
                snapshot.getErrorRatePercent(),
                snapshot.getCostAmount(),
                snapshot.getCurrency()
        );
    }
}
