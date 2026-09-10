package com.felipeshimizu.hybridcloudsimulator.service;

import com.felipeshimizu.hybridcloudsimulator.model.Environment;
import com.felipeshimizu.hybridcloudsimulator.model.MetricSnapshot;
import com.felipeshimizu.hybridcloudsimulator.model.enums.CurrencyType;
import com.felipeshimizu.hybridcloudsimulator.model.enums.EnvironmentType;
import com.felipeshimizu.hybridcloudsimulator.repository.MetricSnapshotRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class MetricSimulationService {
    private static final CurrencyType CURRENCY = CurrencyType.BRL;

    private final EnvironmentService environmentService;
    private final MetricSnapshotRepository metricSnapshotRepository;

    public MetricSimulationService(EnvironmentService environmentService, MetricSnapshotRepository metricSnapshotRepository) {
        this.environmentService = environmentService;
        this.metricSnapshotRepository = metricSnapshotRepository;
    }

    @Scheduled(fixedRateString = "${simulation.interval-ms:5000}")
    public void generateSnapshots() {
        List<Environment> activeEnvironments = environmentService.findActiveEnvironments();
        Instant collectedAt = Instant.now();

        for (Environment environment : activeEnvironments) {
            MetricSnapshot snapshot = createSnapshot(environment, collectedAt);
            metricSnapshotRepository.save(snapshot);
        }
    }

    private MetricSnapshot createSnapshot(Environment environment, Instant collectedAt) {
        if (environment.getType() == EnvironmentType.ON_PREM) {
            return new MetricSnapshot(
                    environment,
                    collectedAt,
                    randomIntBetween(15, 45),
                    randomDecimalBetween(0.10, 1.50),
                    randomDecimalBetween(0.40, 0.90),
                    CURRENCY
            );
        }

        return new MetricSnapshot(
                environment,
                collectedAt,
                randomIntBetween(80, 180),
                randomDecimalBetween(0.50, 4.00),
                randomDecimalBetween(0.90, 2.00),
                CURRENCY
        );
    }

    private int randomIntBetween(int minInclusive, int maxInclusive) {
        return ThreadLocalRandom.current().nextInt(minInclusive, maxInclusive + 1);
    }

    private BigDecimal randomDecimalBetween(double minInclusive, double maxInclusive) {
        double value = ThreadLocalRandom.current().nextDouble(minInclusive, maxInclusive);
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP);
    }

}
