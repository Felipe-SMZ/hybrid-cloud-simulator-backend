CREATE TABLE environments
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(100) NOT NULL,
    type     VARCHAR(20)  NOT NULL,
    location VARCHAR(100) NOT NULL,
    active   BOOLEAN      NOT NULL DEFAULT TRUE
) ENGINE=InnoDB;

CREATE TABLE metric_snapshots
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    environment_id     BIGINT         NOT NULL,
    collected_at       DATETIME       NOT NULL,
    latency_ms         INT            NOT NULL CHECK (latency_ms >= 0),
    error_rate_percent DECIMAL(5, 2)  NOT NULL CHECK (error_rate_percent >= 0 AND error_rate_percent <= 100),
    cost_amount        DECIMAL(12, 2) NOT NULL CHECK (cost_amount >= 0),
    currency           VARCHAR(3)     NOT NULL,
    CONSTRAINT fk_metric_snapshot_environment
        FOREIGN KEY (environment_id)
            REFERENCES environments (id)
) ENGINE=InnoDB;

CREATE INDEX idx_metric_snapshots_collected_at
    ON metric_snapshots (collected_at);

CREATE INDEX idx_metric_snapshots_environment_collected_at
    ON metric_snapshots (environment_id, collected_at);