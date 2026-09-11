package com.felipeshimizu.hybridcloudsimulator.dto;

import com.felipeshimizu.hybridcloudsimulator.model.enums.EnvironmentType;

public record EnvironmentResponse(
        Long id,
        String name,
        EnvironmentType type,
        String location,
        boolean active
) {
}
