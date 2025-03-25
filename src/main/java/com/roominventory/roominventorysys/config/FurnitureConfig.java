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
    @Order(4)
    CommandLineRunner initializeFurniture(FurnitureRepository furnitureRepository) {
        return args -> {
            if (furnitureRepository.count() == 0) {
                System.out.println("Furniture Repository count was 0");
                List<Furniture> furnitureList = new ArrayList<>();

                for (int roomId = 1; roomId <= 220; roomId++) {
                    if(roomId <= 160){
                        addFurniture(furnitureList, roomId);
                    }
                    else{
                        addFurniture(furnitureList, roomId);
                        addFurniture(furnitureList, roomId);
                    }

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

    private void addFurniture(List<Furniture> furnitureList, Integer roomId) {
        furnitureList.add(new Furniture(roomId, "Easy Chair", "GOOD"));
        furnitureList.add(new Furniture(roomId, "Bed", "GOOD"));
        furnitureList.add(new Furniture(roomId, "Mattress", "GOOD"));
        furnitureList.add(new Furniture(roomId, "Closet", "GOOD"));
        furnitureList.add(new Furniture(roomId, "Study Table", "GOOD"));
        furnitureList.add(new Furniture(roomId, "Study Chair", "GOOD"));
        furnitureList.add(new Furniture(roomId, "Wall", "GOOD"));
        furnitureList.add(new Furniture(roomId, "Chest of Draws", "GOOD"));
        furnitureList.add(new Furniture(roomId, "Window", "GOOD"));
    }
}
