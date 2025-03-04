package com.roominventory.roominventorysys.service;

import com.roominventory.roominventorysys.model.Room;
import com.roominventory.roominventorysys.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room addNewRoom(Room room) {
        System.out.println(room);
        return roomRepository.save(room);
    }

    // Get all rooms
    public List<Room> getRooms() {
        return (List<Room>) roomRepository.findAll();
    }

    // Get Room by ID
    public Optional<Room> getRoomById(Integer id) {
        return roomRepository.findById(id);
    }

    @Transactional
    public void updateRoom(Integer roomID, Integer blockID, String roomNumber, Integer floor, Integer maxOccupants) {
        Room room = roomRepository.findById(roomID)
                .orElseThrow(() -> new IllegalArgumentException("Room with id " + roomID + " does not exist"));

        if(blockID != null && !blockID.equals(room.getBlockID())) {
            room.setBlockID(blockID);
        }

        if(roomNumber != null && !roomNumber.equals(room.getRoomNumber())) {
            room.setRoomNumber(roomNumber);
        }

        if(floor != null && !floor.equals(room.getFloor())) {
            room.setFloor(floor);
        }

        if(maxOccupants != null && !maxOccupants.equals(room.getMaxOccupants())) {
            room.setMaxOccupants(maxOccupants);
        }

        roomRepository.save(room);
    }

    // Delete room by ID
    public void deleteRoom(Integer id) {
        roomRepository.deleteById(id);
    }
}
