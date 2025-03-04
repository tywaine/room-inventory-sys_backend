package com.roominventory.roominventorysys.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FurnitureConfig {

    /*
    @Bean
    @Order(3)
    CommandLineRunner initializeFurniture(FurnitureRepository furnitureRepository) {
        return args -> {
            Furniture bed = new Furniture(
                    1,
                    "Bed",
                    "Good"
            );

            Furniture desk = new Furniture(
                    1,
                    "Desk",
                    "POOR"
            );

            Furniture table = new Furniture(
                    1,
                    "Table",
                    "EXCELLENT"
            );

            // Save to database
            furnitureRepository.saveAll(List.of(bed, desk, table));
        };
    }

     */
}
