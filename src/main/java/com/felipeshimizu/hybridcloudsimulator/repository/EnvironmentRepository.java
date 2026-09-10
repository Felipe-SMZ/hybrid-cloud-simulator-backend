package com.felipeshimizu.hybridcloudsimulator.repository;

import com.felipeshimizu.hybridcloudsimulator.model.Environment;
import com.felipeshimizu.hybridcloudsimulator.model.enums.EnvironmentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnvironmentRepository extends JpaRepository<Environment, Long> {

    List<Environment> findByActiveTrueOrderByIdAsc();

    Optional<Environment> findByType(EnvironmentType type);

}
