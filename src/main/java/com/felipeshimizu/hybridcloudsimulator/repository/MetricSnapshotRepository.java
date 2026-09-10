package com.felipeshimizu.hybridcloudsimulator.repository;

import com.felipeshimizu.hybridcloudsimulator.model.MetricSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface MetricSnapshotRepository extends JpaRepository<MetricSnapshot, Long> {

    Optional<MetricSnapshot> findFirstByEnvironmentIdOrderByCollectedAtDesc(Long environmentId);

    List<MetricSnapshot> findByCollectedAtBetweenOrderByCollectedAtAsc(Instant from, Instant to);
}
