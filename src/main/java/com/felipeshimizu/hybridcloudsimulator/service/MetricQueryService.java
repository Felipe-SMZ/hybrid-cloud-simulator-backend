package com.felipeshimizu.hybridcloudsimulator.service;

import com.felipeshimizu.hybridcloudsimulator.dto.MetricHistoryResponse;
import com.felipeshimizu.hybridcloudsimulator.dto.MetricLatestResponse;
import com.felipeshimizu.hybridcloudsimulator.mapper.MetricSnapshotMapper;
import com.felipeshimizu.hybridcloudsimulator.model.Environment;
import com.felipeshimizu.hybridcloudsimulator.repository.MetricSnapshotRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MetricQueryService {

    private final EnvironmentService environmentService;
    private final MetricSnapshotRepository metricSnapshotRepository;
    private final MetricSnapshotMapper metricSnapshotMapper;

    public MetricQueryService(EnvironmentService environmentService, MetricSnapshotRepository metricSnapshotRepository, MetricSnapshotMapper metricSnapshotMapper) {
        this.environmentService = environmentService;
        this.metricSnapshotRepository = metricSnapshotRepository;
        this.metricSnapshotMapper = metricSnapshotMapper;
    }

    public List<MetricLatestResponse> findLatestByEnvironment() {
        return environmentService.findActiveEnvironments().stream()
                .map(Environment::getId)
                .map(metricSnapshotRepository::findFirstByEnvironmentIdOrderByCollectedAtDesc)
                .flatMap(Optional::stream)
                .map(metricSnapshotMapper::toLatestResponse)
                .toList();
    }

    public List<MetricHistoryResponse> findHistory(Instant from, Instant to) {
        validatePeriod(from, to);

        return metricSnapshotRepository.findByCollectedAtBetweenOrderByCollectedAtAsc(from, to)
                .stream()
                .map(metricSnapshotMapper::toHistoryResponse)
                .sorted(Comparator.comparing(MetricHistoryResponse::collectedAt))
                .toList();
    }

    private void validatePeriod(Instant from, Instant to) {
        if (from.isAfter(to)) {
            throw new IllegalArgumentException("O início do período deve ser anterior ao fim.");
        }
    }

}
