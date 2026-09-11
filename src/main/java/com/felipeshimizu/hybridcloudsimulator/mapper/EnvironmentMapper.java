package com.felipeshimizu.hybridcloudsimulator.mapper;

import com.felipeshimizu.hybridcloudsimulator.dto.EnvironmentResponse;
import com.felipeshimizu.hybridcloudsimulator.model.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentMapper {
    public EnvironmentResponse toEnvironmentResponse(Environment environment) {
        return new EnvironmentResponse(
                environment.getId(),
                environment.getName(),
                environment.getType(),
                environment.getLocation(),
                environment.isActive()
        );
    }
}
