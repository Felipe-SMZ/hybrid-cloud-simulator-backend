package com.felipeshimizu.hybridcloudsimulator.controller;

import com.felipeshimizu.hybridcloudsimulator.dto.EnvironmentResponse;
import com.felipeshimizu.hybridcloudsimulator.mapper.EnvironmentMapper;
import com.felipeshimizu.hybridcloudsimulator.service.EnvironmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/environments")
public class EnvironmentController {

    private final EnvironmentService environmentService;
    private final EnvironmentMapper environmentMapper;

    public EnvironmentController(EnvironmentService environmentService, EnvironmentMapper environmentMapper) {
        this.environmentService = environmentService;
        this.environmentMapper = environmentMapper;
    }

    @GetMapping
    public List<EnvironmentResponse> findAllActive() {
        return environmentService.findActiveEnvironments().stream()
                .map(environmentMapper::toEnvironmentResponse)
                .toList();
    }

}
