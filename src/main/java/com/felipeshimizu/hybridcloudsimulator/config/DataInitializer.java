package com.felipeshimizu.hybridcloudsimulator.config;

import com.felipeshimizu.hybridcloudsimulator.model.Environment;
import com.felipeshimizu.hybridcloudsimulator.model.enums.EnvironmentType;
import com.felipeshimizu.hybridcloudsimulator.repository.EnvironmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initializeEnvironments(EnvironmentRepository environmentRepository) {
        return args -> {
            if (environmentRepository.count() == 0) {
                environmentRepository.save(new Environment(
                        "Data Center São Paulo",
                        EnvironmentType.ON_PREM,
                        "São Paulo, BR"
                ));

                environmentRepository.save(new Environment(
                        "Nuvem Pública",
                        EnvironmentType.CLOUD,
                        "Região cloud fictícia"
                ));
            }
        };
    }
}
