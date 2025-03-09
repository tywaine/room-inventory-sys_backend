package com.roominventory.roominventorysys.config;

import com.roominventory.roominventorysys.model.Furniture;
import com.roominventory.roominventorysys.repository.FurnitureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FurnitureConfig {

    @Bean
    @Order(2)
    CommandLineRunner initializeFurniture(FurnitureRepository furnitureRepository) {
        return args -> {
            if (furnitureRepository.count() == 0) {
                System.out.println("Furniture Repository count was 0");
                List<Furniture> furnitureList = new ArrayList<>();

                for (int roomId = 1; roomId <= 220; roomId++) {
                    furnitureList.add(new Furniture(roomId, "Bed", "Good"));
                    furnitureList.add(new Furniture(roomId, "Mattress", "Good"));
                    furnitureList.add(new Furniture(roomId, "Desk", "Good"));
                    furnitureList.add(new Furniture(roomId, "Chair", "Good"));
                    furnitureList.add(new Furniture(roomId, "Wardrobe", "Good"));
                    furnitureList.add(new Furniture(roomId, "Bookshelf", "Good"));
                    furnitureList.add(new Furniture(roomId, "Table", "Good"));
                    furnitureList.add(new Furniture(roomId, "Couch", "Good"));
                    furnitureRepository.saveAll(furnitureList);
                    System.out.println("Furniture added to room " + roomId + " in furnitureList");
                    furnitureList.clear();
                }

                System.out.println("Default furniture data initialized successfully.");
            }
            else {
                System.out.println("Furniture table already populated. Skipping initialization.");
            }
        };


    }
}
