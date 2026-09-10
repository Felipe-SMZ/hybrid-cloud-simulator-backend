package com.felipeshimizu.hybridcloudsimulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HybridCloudSimulatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(HybridCloudSimulatorApplication.class, args);
    }

}
