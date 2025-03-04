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
    @Order(1)
    CommandLineRunner initializeRooms(RoomRepository roomRepository) {
        return args -> {
            List<Room> roomsToSave = new ArrayList<>();

            // Fetch existing room numbers efficiently
            Set<String> existingRoomNumbers = new HashSet<>();
            roomRepository.findAll().forEach(room -> existingRoomNumbers.add(room.getRoomNumber()));

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

                for (int floor = 1; floor <= maxRooms / 10; floor++) {
                    for (int roomNum = 1; roomNum <= 10; roomNum++) {
                        String roomNumber = getRoomNumber(blockID, floor, roomNum);

                        // Only add room if it doesn't exist in the fetched set
                        if (!existingRoomNumbers.contains(roomNumber)) {
                            roomsToSave.add(new Room(blockID, roomNumber, floor, maxOccupants));
                        }
                    }
                }
            }

            // Save only new rooms in a single batch
            if (!roomsToSave.isEmpty()) {
                roomRepository.saveAll(roomsToSave);
            }
        };
    }

    private String getRoomNumber(Integer blockID, int floor, int roomNum) {
        char blockLetter = (char) ('A' + (blockID - 1)); // Convert block ID to letter
        return blockLetter + String.format("%d%02d", floor, roomNum);
    }
}
