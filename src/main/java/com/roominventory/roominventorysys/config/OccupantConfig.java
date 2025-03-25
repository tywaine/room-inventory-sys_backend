package com.roominventory.roominventorysys.config;

import com.roominventory.roominventorysys.model.Occupant;
import com.roominventory.roominventorysys.repository.OccupantRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class OccupantConfig {

    /*
    @Bean
    @Order(5)
    CommandLineRunner initializeOccupants(OccupantRepository occupantRepository) {
        return args -> {
            if (occupantRepository.count() == 0) {
                List<Occupant> newOccupants = List.of(
                        new Occupant(
                                "John",
                                "Doe",
                                "620152332",
                                "john.doe@example.com",
                                "8761234567",
                                1,
                                LocalDateTime.now()),
                        new Occupant(
                                "Jane",
                                "Smith",
                                "620143332",
                                "jane.smith@example.com",
                                "8767654321",
                                2,
                                LocalDateTime.now())
                );

                occupantRepository.saveAll(newOccupants);
                System.out.println("Default occupants initialized successfully.");
            }
            else {
                System.out.println("Occupants already exist. Skipping initialization.");
            }
        };
    }

     */
}
