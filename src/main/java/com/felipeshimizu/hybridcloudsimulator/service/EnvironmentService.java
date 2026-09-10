package com.felipeshimizu.hybridcloudsimulator.service;

import com.felipeshimizu.hybridcloudsimulator.model.Environment;
import com.felipeshimizu.hybridcloudsimulator.repository.EnvironmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvironmentService {

    private final EnvironmentRepository environmentRepository;

    public EnvironmentService(EnvironmentRepository environmentRepository) {
        this.environmentRepository = environmentRepository;
    }

    public List<Environment> findActiveEnvironments() {
        return environmentRepository.findByActiveTrueOrderByIdAsc();
    }
}
