package com.felipeshimizu.hybridcloudsimulator.model;

import com.felipeshimizu.hybridcloudsimulator.model.enums.CurrencyType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "metric_snapshots")
public class MetricSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "environment_id", nullable = false)
    private Environment environment;

    @Column(nullable = false)
    private Instant collectedAt;

    @Column(nullable = false)
    private int latenncyMs;

    @Column(nullable = false)
    private BigDecimal errorRatePercent;

    @Column(nullable = false)
    private BigDecimal costAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrencyType currency;

    protected MetricSnapshot() {
    }

    public MetricSnapshot(Environment environment, Instant collectedAt, int latenncyMs, BigDecimal errorRatePercent, BigDecimal costAmount, CurrencyType currency) {
        this.environment = environment;
        this.collectedAt = collectedAt;
        this.latenncyMs = latenncyMs;
        this.errorRatePercent = errorRatePercent;
        this.costAmount = costAmount;
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public Instant getCollectedAt() {
        return collectedAt;
    }

    public int getLatenncyMs() {
        return latenncyMs;
    }

    public BigDecimal getErrorRatePercent() {
        return errorRatePercent;
    }

    public BigDecimal getCostAmount() {
        return costAmount;
    }

    public CurrencyType getCurrency() {
        return currency;
    }
}
