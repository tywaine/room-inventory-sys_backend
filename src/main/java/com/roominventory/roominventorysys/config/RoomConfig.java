package com.roominventory.roominventorysys.config;

import com.roominventory.roominventorysys.model.Room;
import com.roominventory.roominventorysys.repository.RoomRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.*;

@Configuration
public class RoomConfig {

    @Bean
    @Order(3)
    CommandLineRunner initializeRooms(RoomRepository roomRepository) {
        return args -> {
            if (roomRepository.count() == 0) {
                List<Room> rooms = new ArrayList<>();

                // Define block information
                Map<Integer, int[]> blockInfo = new LinkedHashMap<>();
                blockInfo.put(1, new int[]{40, 1});
                blockInfo.put(2, new int[]{40, 1});
                blockInfo.put(3, new int[]{40, 1});
                blockInfo.put(4, new int[]{40, 1});
                blockInfo.put(5, new int[]{20, 2});
                blockInfo.put(6, new int[]{20, 2});
                blockInfo.put(7, new int[]{20, 2});

                // Generate rooms
                for (Integer blockID : blockInfo.keySet()) {
                    int maxRooms = blockInfo.get(blockID)[0];
                    int maxOccupants = blockInfo.get(blockID)[1];

                    for (int roomNum = 1; roomNum <= maxRooms; roomNum++) {
                        String roomNumber = getRoomNumber(blockID, roomNum);
                        rooms.add(new Room(blockID, roomNumber, maxOccupants));
                    }
                }

                roomRepository.saveAll(rooms);
                System.out.println("Default room data initialized successfully.");
            }
            else {
                System.out.println("Room table already populated. Skipping initialization.");
            }
        };
    }

    private String getRoomNumber(Integer blockID, int roomNum) {
        char blockLetter = (char) ('A' + (blockID - 1)); // Convert block ID to letter
        return blockLetter + String.valueOf(roomNum);
    }
}
